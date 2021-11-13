package com.mashup.kkyuni.feature.calendar.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.calendar.presentation.SnapPageScrollListener.OnChangeListener
import com.mashup.kkyuni.feature.calendar.presentation.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CalendarFragment : BindingFragment<FragmentCalendarBinding>(R.layout.fragment_calendar) {

    private val viewModel: CalendarViewModel by viewModels()

    private val adapter by lazy { CalendarDayAdapter() }
    private val snapHelper by lazy { PagerSnapHelper() }

    private var firstCompleteVisibleItemPosition = -1
    private var lastCompleteVisibleItemPosition = -1

    private var startDate: Date? = null
    private var endDate: Date? = null

    private lateinit var baseDateList: ArrayList<Date>

    private var formatter = SimpleDateFormat("dd-MM-yyyy", Locale.KOREA)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = this.viewModel
        initView()

        viewModel.run {
            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                    onSetting.collect {
                        CalendarFragmentDirections.actionToSetting().run {
                            findNavController().navigate(this)
                        }
                    }

                    onPlayList.collect {
                        // 여기로 year, month 넘겨주세요
                        // navigateToPlayListFragment()
                    }

                    preview.collect {
                        binding.previewGroup.isVisible = it.not()
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerView.adapter = this.adapter
        snapHelper.attachToRecyclerView(binding.recyclerView)

        val snapPagerScrollListener = object : SnapPageScrollListener(
            snapHelper = snapHelper,
            type = ON_SETTLED,
            notifyOnInit = true,
            listener = OnChangeListener {
                val time = Date(baseDateList[it].time)
                Calendar.getInstance().run {
                    this.time = time
                    val year = get(Calendar.YEAR)
                    val month = get(Calendar.MONTH) + 1
                    var day = get(Calendar.DATE).toString()
                    if (day.toInt() < 10) {
                        day = "0$day"
                    }
                    viewModel.requestDiary("$year-$month-$day")
                }
            }
        ) {}

        binding.recyclerView.addOnScrollListener(snapPagerScrollListener)

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                val totalItemCount = layoutManager?.itemCount

                firstCompleteVisibleItemPosition =
                    layoutManager?.findFirstCompletelyVisibleItemPosition()!!
                lastCompleteVisibleItemPosition =
                    layoutManager?.findLastCompletelyVisibleItemPosition()!!

                if (lastCompleteVisibleItemPosition == 0) {
                    if (dy < 0 || dx < 0) {
                        if (dx < 0) {
                            Calendar.getInstance().run {
                                time = endDate
                                add(Calendar.MONTH, -1)

                                val tempEndData = time
                                binding.yearOfMonth.text =
                                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}"

                                baseDateList = getDateList(
                                    formatter.format(tempEndData),
                                    formatter.format(startDate)
                                )
                                setDateList(baseDateList)
                                endDate = tempEndData
                            }
                        }
                    }
                } else if (totalItemCount != null) {
                    if (lastCompleteVisibleItemPosition == totalItemCount - 1) {
                        Calendar.getInstance().run {
                            val date = baseDateList[lastCompleteVisibleItemPosition]

                            time = date
                            binding.yearOfMonth.text =
                                "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}"
                        }
                    } else {
                        if (dx < 0) {
                            Calendar.getInstance().run {
                                val date = baseDateList[firstCompleteVisibleItemPosition + 1]
                                time = date
                                binding.yearOfMonth.text =
                                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}"
                            }
                        } else {
                            Calendar.getInstance().run {
                                val date = baseDateList[lastCompleteVisibleItemPosition]
                                time = date
                                binding.yearOfMonth.text =
                                    "${get(Calendar.YEAR)}.${get(Calendar.MONTH) + 1}"
                            }
                        }
                    }
                }
            }
        })

        formatter = SimpleDateFormat("dd-MM-yyyy", Locale.KOREA)
        startDate = Date(START_DATE)

        Calendar.getInstance().run {
            time = endDate ?: Date(System.currentTimeMillis() + ONE_WEEK)
            endDate = time
            baseDateList = getDateList(formatter.format(startDate), formatter.format(endDate))
            setDateList(baseDateList)
            val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            // 현재 위치 찾아서 포지셔닝
            layoutManager.scrollToPosition(baseDateList.size - 10)
        }
    }

    private fun setDateList(dateList: ArrayList<Date>) {
        val calendar = Calendar.getInstance()

        val list = dateList.map {
            calendar.time = it
            val dayOfWeek = calendar.get(Calendar.DATE)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            CalendarDayModel(
                "$year-$month-$dayOfWeek"
            )
        }

        adapter.submitList(list)
    }

    private fun getDateList(startDateString: String, endDateString: String): ArrayList<Date> {
        val dateList = ArrayList<Date>()
        var startDate: Date? = null

        kotlin.runCatching {
            startDate = formatter.parse(startDateString)
        }

        var endDate: Date? = null
        kotlin.runCatching {
            endDate = formatter.parse(endDateString)
        }

        val interval = (24 * 1000 * 60 * 60).toLong()
        val endTime = endDate?.time ?: -1
        var currentTime = startDate?.time ?: -1

        while (currentTime <= endTime) {
            dateList.add(Date(currentTime))
            currentTime += interval
        }

        return dateList
    }

    private fun navigateToPlayListFragment(year: Int, month: Int) {
        CalendarFragmentDirections.actionToPlayList(year, month).run {
            findNavController().navigate(this)
        }
    }

    companion object {
        // 2021.09.01 09:00:00
        const val START_DATE = 1630486800000L

        // 1주일 시간
        const val ONE_WEEK = 604800000L
    }
}