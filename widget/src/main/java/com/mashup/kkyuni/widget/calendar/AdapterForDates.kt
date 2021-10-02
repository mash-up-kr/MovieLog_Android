package com.mashup.kkyuni.widget.calendar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.widget.R
import com.mashup.kkyuni.widget.databinding.ItemCalendarDateBinding
import java.text.DateFormatSymbols
import java.util.*

class AdapterForDates(
    private val mModelItems: ArrayList<Model>,
    private val mContext: Context,
    private val mDatesList: ArrayList<String>,
    private val fullFormatDate: ArrayList<Date>,
    private val dateItemClickListener: DateItemClickListener
) : ListAdapter<Model, AdapterForDates.ViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<Model>() {
        override fun areItemsTheSame(oldItem: Model, newItem: Model) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Model, newItem: Model) = oldItem == newItem
    }

    private var mPreviousIndex: Int? = -1
    private var mStrokeColor: Int? = null
    private var mUnSelectedColor: Int? = null
    private var mSelectedColor: Int? = null
    private var mStrokeWidth: Float? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            ItemCalendarDateBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mMonths = DateFormatSymbols().months
        holder.binding.date.text = mModelItems[position].date
        holder.binding.date.setTextColor(
            attributes!!.getColor(
                R.styleable.HorizontalCalender_android_textColor,
                0
            )
        )

        if (position == mModelItems.size - 1) {
            holder.binding.day.text = mContext.getString(R.string.today)
        } else {
            holder.binding.day.text = mModelItems[position].day[0].toString()
        }

        if (attributes!!.getBoolean(R.styleable.HorizontalCalender_dayView, true)) {
            holder.binding.day.visibility = View.VISIBLE
        } else {
            holder.binding.day.visibility = View.GONE
        }

        holder.binding.layout.setOnClickListener {
            val date = mDatesList[position]
            dateItemClickListener.onDateClick(date)

            val calendar = Calendar.getInstance()
            calendar.time = fullFormatDate[position]
            mMonth = mMonths[calendar.get(Calendar.MONTH)] + ", " + calendar.get(Calendar.YEAR)
            mPreviousIndex = position
        }


        setSelectedColorA(attributes?.getColor(R.styleable.HorizontalCalender_selectedColor, 0)!!)
        setUnSelectedColorA(
            attributes?.getColor(
                R.styleable.HorizontalCalender_unSelectedColor,
                0
            )!!
        )
        setStrokeWidthA(attributes?.getDimension(R.styleable.HorizontalCalender_strokeWidth, 0F)!!)
        setStrokeColorA(attributes?.getColor(R.styleable.HorizontalCalender_strokeColor, 0)!!)

        mStrokeColor = getStrokeColorA()
        mUnSelectedColor = getUnSelectedColorA()
        mSelectedColor = getSelectedColorA()
        mStrokeWidth = getStrokeWidthA()

        mStrokeWidth = if (mStrokeWidth == 0F) {
            2f
        } else {
            getStrokeWidthA()
        }

        mStrokeColor = if (mStrokeColor == 0) {
            Color.BLUE
        } else {
            getStrokeColorA()
        }

        mUnSelectedColor = if (mUnSelectedColor == 0) {
            Color.CYAN
        } else {
            getUnSelectedColorA()
        }


        mSelectedColor = if (mSelectedColor == 0) {
            Color.WHITE
        } else {
            getSelectedColorA()
        }


        if (mPreviousIndex == position) {

            val shape = GradientDrawable()
            shape.shape = GradientDrawable.OVAL
            shape.setStroke(mStrokeWidth!!.toInt(), mStrokeColor!!)
            shape.setColor(mSelectedColor!!)

        } else {

            val shape = GradientDrawable()
            shape.shape = GradientDrawable.OVAL

            shape.setColor(mUnSelectedColor!!)
        }
    }

    class ViewHolder(val binding: ItemCalendarDateBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        var mMonth: String? = null
        var attributes: TypedArray? = null
    }

    private fun setSelectedColorA(color: Int) {
        this.mSelectedColor = color
    }

    private fun getSelectedColorA(): Int? {
        return mSelectedColor
    }

    private fun setUnSelectedColorA(color: Int) {
        this.mUnSelectedColor = color
    }

    private fun getUnSelectedColorA(): Int? {
        return mUnSelectedColor
    }

    private fun setStrokeColorA(color: Int) {
        this.mStrokeColor = color
    }

    private fun getStrokeColorA(): Int? {
        return mStrokeColor
    }

    private fun setStrokeWidthA(width: Float) {
        this.mStrokeWidth = width
    }

    private fun getStrokeWidthA(): Float? {
        return mStrokeWidth
    }
}