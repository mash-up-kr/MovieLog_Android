package com.mashup.kkyuni.widget.calendar

import android.app.DatePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.widget.R
import com.mashup.kkyuni.widget.calendar.CalendarAdapter.Companion.attributes
import com.mashup.kkyuni.widget.databinding.CalenderViewBinding
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HorizontalCalender @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private var firstCompleteVisibleItemPosition = -1
    private var lastCompleteVisibleItemPosition = -1
    private var endDate: Date? = null
    private var months = DateFormatSymbols().months
    private var startD: Date? = null
    private lateinit var baseDateList: ArrayList<Date>
    private val calendar = Calendar.getInstance()
    private var formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    private var textColor: Int? = null
    private var showTodayIcon: Boolean? = null

    private val binding = CalenderViewBinding.inflate(LayoutInflater.from(context))

    init {
        attributes = context.obtainStyledAttributes(attrs, R.styleable.HorizontalCalender)
        setShowTodayIcon(
            attributes?.getBoolean(
                R.styleable.HorizontalCalender_showTodayIcon,
                false
            )
        )
        setTextColorA(attributes!!.getColor(R.styleable.HorizontalCalender_android_textColor, 0))
        binding.month.setTextColor(getTextColorA()!!)
    }

    fun initialize(dateItemClickListener: DateItemClickListener) {
        horizontalDates(dateItemClickListener)
    }

    private fun horizontalDates(dateItemClickListener: DateItemClickListener) {
        formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        startD = Date()
        val calendar = Calendar.getInstance()
        calendar.time = startD
        val currentMonth = months[calendar.get(Calendar.MONTH)]
        val currentYear = calendar.get(Calendar.YEAR).toString()
        binding.month.text = "$currentMonth ,$currentYear"
        calendar.add(Calendar.MONTH, -1)
        endDate = calendar.time
        baseDateList = getDates(formatter.format(endDate), formatter.format(startD))
        setAdapter(baseDateList, dateItemClickListener)
        val layoutManager3 = binding.recyclerView.layoutManager as? LinearLayoutManager
        layoutManager3?.scrollToPosition(baseDateList.size - 1)
    }

    private fun getDates(dateString1: String, dateString2: String): ArrayList<Date> {

        val mDates = ArrayList<Date>()
        var startDate: Date? = null
        try {
            startDate = formatter.parse(dateString1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        var endDate: Date? = null
        try {
            endDate = formatter.parse(dateString2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val interval = (24 * 1000 * 60 * 60).toLong()
        val endTime = endDate!!.time
        var curTime = startDate!!.time
        while (curTime <= endTime) {
            mDates.add(Date(curTime))
            curTime += interval
        }

        return mDates
    }

    private fun setAdapter(
        dates: ArrayList<Date>,
        dateItemClickListener: DateItemClickListener
    ) {

        val finalDateList = ArrayList<Model>()
        val clickedDate = ArrayList<String>()

        for (i in 0 until dates.size) {
            val lDate = dates[i]
            val c = Calendar.getInstance()
            c.time = lDate

            val dayOfWeek = c.get(Calendar.DATE)
            val monthh = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val dayLongName =
                c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
            finalDateList.add(Model(dayOfWeek.toString(), dayLongName))
            clickedDate.add(String.format("%02d-%02d-%04d", dayOfWeek, monthh + 1, year))
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.stackFromEnd = true
        binding.recyclerView.layoutManager = layoutManager
        layoutManager.scrollToPosition(30)
        val adapter = CalendarAdapter(finalDateList, context, clickedDate, dates, dateItemClickListener)
        binding.recyclerView.adapter = adapter

        adapter.submitList(finalDateList)

        binding.today.setOnClickListener {
            layoutManager.scrollToPosition(finalDateList.size - 1)
            binding.today.visibility = View.GONE
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                Log.d("date", calendar.time.toString())
                val calendarSelect = Calendar.getInstance()
                calendarSelect.time = calendar.time
                calendarSelect.add(Calendar.MONTH, -1)
                val endDate = calendarSelect.time
                binding.month.text =
                    months[calendarSelect.get(Calendar.MONTH)] + ", " + calendarSelect.get(Calendar.YEAR)
                baseDateList = getDates(formatter.format(endDate), formatter.format(startD))
                setAdapter(baseDateList, dateItemClickListener)
                this.endDate = endDate
                if (baseDateList.size > 30) {
                    binding.recyclerView.smoothScrollToPosition(32)
                } else {
                    binding.recyclerView.smoothScrollToPosition(30)
                }
            }



        binding.month.setOnClickListener {
            DatePickerDialog(
                context,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
            calendar.add(Calendar.MONTH, -1)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManagerForPos = recyclerView.layoutManager
                val totalItemCount = layoutManagerForPos?.itemCount

                if (layoutManagerForPos is GridLayoutManager) {
                    val gridLayoutManager = layoutManagerForPos as GridLayoutManager?
                    firstCompleteVisibleItemPosition =
                        gridLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                    lastCompleteVisibleItemPosition =
                        gridLayoutManager.findLastCompletelyVisibleItemPosition()
                } else if (layoutManagerForPos is LinearLayoutManager) {
                    val linearLayoutManager = layoutManagerForPos as LinearLayoutManager?
                    firstCompleteVisibleItemPosition =
                        linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                    lastCompleteVisibleItemPosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition()
                }
                if (firstCompleteVisibleItemPosition == 0) {
                    if (dy < 0 || dx < 0) {
                        if (dx < 0) {
                            Log.d("status", "Scrolled LEFT")
                            if (getShowTodayIcon()!!) binding.today.visibility = View.VISIBLE
                            val calendar = Calendar.getInstance()
                            calendar.time = endDate
                            calendar.add(Calendar.MONTH, -1)
                            val tempEndDate = calendar.time
                            binding.month.text =
                                months[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
                            baseDateList =
                                getDates(formatter.format(tempEndDate), formatter.format(startD))
                            setAdapter(baseDateList, dateItemClickListener)
                            endDate = tempEndDate
                        }
                    }
                } else if (totalItemCount != null) {
                    if (lastCompleteVisibleItemPosition == totalItemCount - 1) {
                        val calendar = Calendar.getInstance()
                        val date = baseDateList[lastCompleteVisibleItemPosition]
                        calendar.time = date
                        binding.month.text =
                            months[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)

                        if (dy > 0 || dx > 0) {
                            if (dy > 0) {
                                Log.d("status", "Scrolled TOP")
                            }
                            if (dx > 0) {
                                Log.d("status", "Scrolled RIGHT")
                                binding.today.visibility = View.GONE
                            }
                        }
                    } else {
                        if (dx < 0) {
                            Log.d("status", "Scrolled LEFT")
                            if (getShowTodayIcon()!!) binding.today.visibility = View.VISIBLE
                            val calendar = Calendar.getInstance()
                            val date = baseDateList[firstCompleteVisibleItemPosition + 1]
                            calendar.time = date
                            binding.month.text =
                                months[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
                        } else {
                            val calendar = Calendar.getInstance()
                            val date = baseDateList[lastCompleteVisibleItemPosition]
                            calendar.time = date
                            binding.month.text =
                                months[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
                        }
                    }
                }
            }
        })
    }

    private fun setTextColorA(int: Int) {
        this.textColor = int
    }

    private fun getTextColorA(): Int? {
        return textColor
    }


    private fun setShowTodayIcon(image: Boolean?) {
        this.showTodayIcon = image
    }

    private fun getShowTodayIcon(): Boolean? {
        return showTodayIcon
    }

}

data class Model(val date: String, val day: String)