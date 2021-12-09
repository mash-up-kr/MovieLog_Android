package com.mashup.kkyuni.feature.calendar.presentation

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.calendar.presentation.SnapPageScrollListener.OnChangeListener
import com.mashup.kkyuni.feature.calendar.presentation.databinding.FragmentCalendarBinding
import com.mashup.kkyuni.feature.writing.presentation.WritingFragmentDirections
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

    var previousDate: String? = null

    private var formatter = SimpleDateFormat("dd-MM-yyyy", Locale.KOREA)
    private lateinit var snapPagerScrollListener: SnapPageScrollListener

    var year = -1
    var month = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = this.viewModel
        initView()
        initWebView()

        viewModel.run {
            viewLifecycleOwner.lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        onSetting.collect {
                            CalendarFragmentDirections.actionToSetting().run {
                                findNavController().navigate(this)
                            }
                        }
                    }

                    launch {
                        preview.collect {
                            binding.previewGroup.isVisible = it.not()
                        }
                    }

                    launch {
                        onWriting.collect {
                            CalendarFragmentDirections.actionToWriting(it).run {
                                findNavController().navigate(this)
                            }
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                onPlayList.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect {
                    navigateToPlayListFragment(year, month)
                }
            }
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("date")
            ?.observe(viewLifecycleOwner) { result ->
                previousDate = result
                initView()
            }
    }

    private fun initView() {
        binding.recyclerView.adapter = this.adapter
        snapHelper.attachToRecyclerView(binding.recyclerView)

        snapPagerScrollListener = object : SnapPageScrollListener(
            snapHelper = snapHelper,
            type = ON_SETTLED,
            notifyOnInit = true,
            listener = OnChangeListener {
                val time = Date(baseDateList[it].time)
                Calendar.getInstance().run {
                    this.time = time
                    year = get(Calendar.YEAR)
                    month = get(Calendar.MONTH) + 1
                    var day = get(Calendar.DATE).toString()
                    if (day.toInt() < 10) {
                        day = "0$day"
                    }
                    val token = viewModel.getUserAccessToken().orEmpty()
                    binding.webView.loadUrl("https://compassionate-wing-0abef6.netlify.app/?token=$token&date=$year-$month-$day")
                    viewModel.updateCurrentDate("$year-$month-$day")
                }
            }
        ) {}

        binding.recyclerView.addOnScrollListener(snapPagerScrollListener)

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                val totalItemCount = baseDateList.size

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
                                val date = baseDateList[baseDateList.size - 10]
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
            baseDateList =
                getDateList(formatter.format(startDate), formatter.format(endDate), previousDate)
            setDateList(baseDateList)
            val layoutManager = binding.recyclerView.layoutManager as LinearLayoutManager
            // 현재 위치 찾아서 포지셔닝
            layoutManager.scrollToPosition(baseDateList.size - 10)
        }
    }

    private fun initWebView() {
        with(binding.webView) {
            webViewClient = object : WebViewClient() {

            }

            settings.run {
                javaScriptEnabled = true
            }
            val token = viewModel.getUserAccessToken().orEmpty()
        }
    }

    private fun setDateList(
        dateList: ArrayList<Date>
    ) {
        val calendar = Calendar.getInstance()

        val list = dateList.map {
            calendar.time = it
            val dayOfWeek = calendar.get(Calendar.DATE)
            val month = calendar.get(Calendar.MONTH) + 1
            val year = calendar.get(Calendar.YEAR)

            CalendarDayModel(
                "$year-$month-$dayOfWeek"
            )
        }

        adapter.submitList(list)
    }

    private fun getDateList(
        startDateString: String,
        endDateString: String,
        previousDate: String? = null
    ): ArrayList<Date> {
        val dateList = ArrayList<Date>()
        var startDate: Date? = null

        kotlin.runCatching {
            startDate = formatter.parse(startDateString)
        }

        var endDate: Date? = null
        kotlin.runCatching {
            previousDate?.let {
                endDate = formatter.parse("15-$it")
            } ?: run {
                endDate = formatter.parse(endDateString)
            }
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
        findNavController().navigate(
            R.id.playListFragment,
            bundleOf(
                "year" to year,
                "month" to month
            )
        )
    }

    companion object {
        // 2021.09.01 09:00:00
        const val START_DATE = 1630486800000L

        // 1주일 시간
        const val ONE_WEEK = 604800000L
    }
}