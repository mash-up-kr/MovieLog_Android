package com.mashup.kkyuni.feature.playlist.presentation.widget

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BottomSheetBindingDialogFragment
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.presentation.PlayListViewModel
import com.mashup.kkyuni.feature.playlist.presentation.R
import com.mashup.kkyuni.feature.playlist.presentation.databinding.DialogChoiceDateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChoiceDateDialogFragment: BottomSheetBindingDialogFragment<DialogChoiceDateBinding>(R.layout.dialog_choice_date){
    private val choiceDateViewModel by viewModels<ChoiceDateViewModel>()
    private val choiceDateAdapter by lazy { ChoiceDateAdapter() }
    private val snapHelper by lazy { PagerSnapHelper() }
    private val playListViewModel by viewModels<PlayListViewModel> ({ requireParentFragment() })

    override fun getTheme(): Int {
        return R.style.ChoiceDateBottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        collectFlows()
    }

    private fun initView() {
        binding.run {
            viewModel = choiceDateViewModel

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

                    val offset = resources.getDimensionPixelSize(R.dimen.choice_date_item_offset)

                    outRect.top = offset
                    outRect.bottom = offset
                }
            })

            recyclerViewDate.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                var currentPosition = RecyclerView.NO_POSITION

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if(newState != RecyclerView.SCROLL_STATE_IDLE) return

                    // 중앙 부분 선택 구현
                    val view = snapHelper.findSnapView(recyclerView.layoutManager) ?: return
                    val position = recyclerView.layoutManager?.getPosition(view) ?: return

                    if(currentPosition != position) {
                        currentPosition = position
                        choiceDateViewModel.selectByPosition(currentPosition)
                    }

                    // 달 선택 무한 스크롤 구현
                    (recyclerView.layoutManager as? LinearLayoutManager)?.let {
                        val visibleItemCount = it.childCount
                        val totalItemCount = it.itemCount

                        val lastPosition = it.findLastVisibleItemPosition()

                        when {
                            lastPosition < visibleItemCount + 2 -> choiceDateViewModel.addPreviousYear()

                            totalItemCount - lastPosition + 1 < visibleItemCount -> choiceDateViewModel.addAfterYear()
                        }
                    }
                }
            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun collectFlows(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                choiceDateViewModel.run {
                    launch {
                        changedNotifyEvent.collect {
                            choiceDateAdapter.notifyDataSetChanged()
                        }
                    }

                    launch {
                        choiceDateEvent.collect {
                            updateDate(it)

                            dismiss()
                        }
                    }

                    launch {
                        dismissEvent.collect {
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun updateDate(date: Date){
        playListViewModel.updateDate(date.year, date.month)
    }

    companion object {
        const val TAG = "ChoiceDateDialog"
        const val KEY_YEAR = "year"
        const val KEY_MONTH = "month"

        fun showDialog(fragmentManager: FragmentManager, date: Date){
            ChoiceDateDialogFragment().also {
                it.arguments = bundleOf(
                    KEY_YEAR to date.year,
                    KEY_MONTH to date.month
                )
                it.show(fragmentManager, TAG)
            }
        }
    }
}