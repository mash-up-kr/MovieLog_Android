package com.mashup.kkyuni.feature.playlist.presentation.widget

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BottomSheetBindingDialogFragment
import com.mashup.kkyuni.feature.playlist.presentation.R
import com.mashup.kkyuni.feature.playlist.presentation.databinding.DialogChoiceDateBinding

class ChoiceDateDialogFragment: BottomSheetBindingDialogFragment<DialogChoiceDateBinding>(R.layout.dialog_choice_date){
    private val choiceDateAdapter by lazy { ChoiceDateAdapter() }
    private val snapHelper by lazy { PagerSnapHelper() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.run {
            recyclerViewDate.adapter = choiceDateAdapter

            snapHelper.attachToRecyclerView(recyclerViewDate)

            recyclerViewDate.addItemDecoration(object : RecyclerView.ItemDecoration(){
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)

                    val position = parent.getChildAdapterPosition(view)
                    val lastPosition = state.itemCount - 1

                    val offset = resources.getDimensionPixelSize(R.dimen.choice_date_item_offset)
                    when(position){
                        0 -> outRect.bottom = offset
                        lastPosition -> outRect.top = offset
                        else -> {
                            outRect.top = offset
                            outRect.bottom = offset
                        }
                    }
                }
            })

        }
    }

    companion object {
        const val KEY_YEAR = "year"
        const val KEY_MONTH = "month"
    }
}