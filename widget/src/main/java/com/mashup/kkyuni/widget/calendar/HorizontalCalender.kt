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
import com.mashup.kkyuni.widget.calendar.AdapterForDates.Companion.attributes
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

    private var mFirstCompleteVisibleItemPosition = -1
    private var mLastCompleteVisibleItemPosition = -1
    private var mEndDate: Date? = null
    private var mMonths = DateFormatSymbols().months
    private var mStartD: Date? = null
    private lateinit var mBaseDateList: ArrayList<Date>
    private val mCal = Calendar.getInstance()
    private var mFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    private var mTextColor: Int? = null
    private var mShowTodayIcon: Boolean? = null

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
        mFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        mStartD = Date()
        val calendar = Calendar.getInstance()
        calendar.time = mStartD
        val currentMonth = mMonths[calendar.get(Calendar.MONTH)]
        val currentYear = calendar.get(Calendar.YEAR).toString()
        binding.month.text = "$currentMonth ,$currentYear"
        calendar.add(Calendar.MONTH, -1)
        mEndDate = calendar.time
        mBaseDateList = getDates(mFormatter.format(mEndDate), mFormatter.format(mStartD))
        setAdapter(mBaseDateList, dateItemClickListener)
        val layoutManager3 = binding.datesRv.layoutManager as? LinearLayoutManager
        layoutManager3?.scrollToPosition(mBaseDateList.size - 1)
    }

    private fun getDates(dateString1: String, dateString2: String): ArrayList<Date> {

        val mDates = ArrayList<Date>()
        var startDate: Date? = null
        try {
            startDate = mFormatter.parse(dateString1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        var endDate: Date? = null
        try {
            endDate = mFormatter.parse(dateString2)
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

        val mFinalDates = ArrayList<Model>()
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
            mFinalDates.add(Model(dayOfWeek.toString(), dayLongName))
            clickedDate.add(String.format("%02d-%02d-%04d", dayOfWeek, monthh + 1, year))
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.stackFromEnd = true
        binding.datesRv.layoutManager = layoutManager
        layoutManager.scrollToPosition(30)

        val adapter =
            AdapterForDates(mFinalDates, context, clickedDate, dates, dateItemClickListener)
        binding.datesRv.adapter = adapter

        binding.today.setOnClickListener {
            layoutManager.scrollToPosition(mFinalDates.size - 1)
            binding.today.visibility = View.GONE
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                mCal.set(Calendar.YEAR, year)
                mCal.set(Calendar.MONTH, monthOfYear)
                mCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                Log.d("date", mCal.time.toString())
                val calendarSelect = Calendar.getInstance()
                calendarSelect.time = mCal.time
                calendarSelect.add(Calendar.MONTH, -1)
                val endDate = calendarSelect.time
                binding.month.text =
                    mMonths[calendarSelect.get(Calendar.MONTH)] + ", " + calendarSelect.get(Calendar.YEAR)
                mBaseDateList = getDates(mFormatter.format(endDate), mFormatter.format(mStartD))
                setAdapter(mBaseDateList, dateItemClickListener)
                mEndDate = endDate
                if (mBaseDateList.size > 30) {
                    binding.datesRv.smoothScrollToPosition(32)
                } else {
                    binding.datesRv.smoothScrollToPosition(30)
                }
            }



        binding.month.setOnClickListener {
            DatePickerDialog(
                context,
                dateSetListener,
                mCal.get(Calendar.YEAR),
                mCal.get(Calendar.MONTH),
                mCal.get(Calendar.DAY_OF_MONTH)
            ).show()
            mCal.add(Calendar.MONTH, -1)
        }

        binding.datesRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManagerForPos = recyclerView.layoutManager
                val totalItemCount = layoutManagerForPos?.itemCount

                if (layoutManagerForPos is GridLayoutManager) {
                    val gridLayoutManager = layoutManagerForPos as GridLayoutManager?
                    mFirstCompleteVisibleItemPosition =
                        gridLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                    mLastCompleteVisibleItemPosition =
                        gridLayoutManager.findLastCompletelyVisibleItemPosition()
                } else if (layoutManagerForPos is LinearLayoutManager) {
                    val linearLayoutManager = layoutManagerForPos as LinearLayoutManager?
                    mFirstCompleteVisibleItemPosition =
                        linearLayoutManager!!.findFirstCompletelyVisibleItemPosition()
                    mLastCompleteVisibleItemPosition =
                        linearLayoutManager.findLastCompletelyVisibleItemPosition()
                }
                if (mFirstCompleteVisibleItemPosition == 0) {
                    if (dy < 0 || dx < 0) {
                        if (dx < 0) {
                            Log.d("status", "Scrolled LEFT")
                            if (getShowTodayIcon()!!) binding.today.visibility = View.VISIBLE
                            val calendar = Calendar.getInstance()
                            calendar.time = mEndDate
                            calendar.add(Calendar.MONTH, -1)
                            val tempEndDate = calendar.time
                            binding.month.text =
                                mMonths[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
                            mBaseDateList =
                                getDates(mFormatter.format(tempEndDate), mFormatter.format(mStartD))
                            setAdapter(mBaseDateList, dateItemClickListener)
                            mEndDate = tempEndDate
                        }
                    }
                } else if (totalItemCount != null) {
                    if (mLastCompleteVisibleItemPosition == totalItemCount - 1) {
                        val calendar = Calendar.getInstance()
                        val date = mBaseDateList[mLastCompleteVisibleItemPosition]
                        calendar.time = date
                        binding.month.text =
                            mMonths[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)

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
                            val date = mBaseDateList[mFirstCompleteVisibleItemPosition + 1]
                            calendar.time = date
                            binding.month.text =
                                mMonths[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
                        } else {
                            val calendar = Calendar.getInstance()
                            val date = mBaseDateList[mLastCompleteVisibleItemPosition]
                            calendar.time = date
                            binding.month.text =
                                mMonths[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
                        }
                    }
                }
            }
        })
    }

    private fun setTextColorA(int: Int) {
        this.mTextColor = int
    }

    private fun getTextColorA(): Int? {
        return mTextColor
    }


    private fun setShowTodayIcon(image: Boolean?) {
        this.mShowTodayIcon = image
    }

    private fun getShowTodayIcon(): Boolean? {
        return mShowTodayIcon
    }

}

data class Model(val date: String, val day: String)