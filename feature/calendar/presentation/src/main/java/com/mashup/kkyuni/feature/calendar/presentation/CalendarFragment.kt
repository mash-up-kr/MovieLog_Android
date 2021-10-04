package com.mashup.kkyuni.feature.calendar.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.calendar.presentation.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
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
    private var months = DateFormatSymbols().months

    private lateinit var baseDateList: ArrayList<Date>

    private var formatter = SimpleDateFormat("dd-MM-yyyy", Locale.KOREA)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = this.viewModel
        initView()

        viewModel.run {
            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    onSetting.collect {

                    }

                    onPlayList.collect {

                    }
                }
            }
        }
    }

    private fun initView() {
        binding.recyclerView.adapter = this.adapter
        snapHelper.attachToRecyclerView(binding.recyclerView)

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
        startDate = Date(System.currentTimeMillis() + 100000000000L)
        
        Calendar.getInstance().run {
            time = startDate
            val currentMonth = months[get(Calendar.MONTH)]
            val currentYear = get(Calendar.YEAR).toString()
            add(Calendar.MONTH, -1)
            endDate = time
            baseDateList = getDateList(formatter.format(endDate), formatter.format(startDate))
            setDateList(baseDateList)
            val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            // 현재 위치 찾아서 포지셔닝
            layoutManager.scrollToPosition(baseDateList.size - 30)
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

    private fun getDateList(dateString1: String, dateString2: String): ArrayList<Date> {
        val dateList = ArrayList<Date>()
        var startDate: Date? = null

        kotlin.runCatching {
            startDate = formatter.parse(dateString1)
        }

        var endDate: Date? = null
        kotlin.runCatching {
            endDate = formatter.parse(dateString2)
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
}