package com.mashup.kkyuni.feature.calendar.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.calendar.presentation.databinding.FragmentKobaCalendarBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class KobaCalendarFragment : BindingFragment<FragmentKobaCalendarBinding>(R.layout.fragment_koba_calendar) {
	private val adapter by lazy { KobaCalendarDayAdapter() }
	private val viewModel: KobaCalendarViewModel by viewModels()
	private val snapHelper by lazy { PagerSnapHelper() }

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initView()

	}

	private fun initView() {
		binding.viewModel = viewModel

		binding.recyclerView.adapter = adapter
		snapHelper.attachToRecyclerView(binding.recyclerView)

		binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
			var currentPosition = RecyclerView.NO_POSITION

			override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
				super.onScrollStateChanged(recyclerView, newState)

				if(newState != RecyclerView.SCROLL_STATE_IDLE) return

				// 중앙 부분 선택 구현
				val view = snapHelper.findSnapView(recyclerView.layoutManager) ?: return
				val position = recyclerView.layoutManager?.getPosition(view) ?: return

				if(currentPosition != position) {
					currentPosition = position
					viewModel.updateCurrentCalendarDate(
						adapter.currentList[currentPosition]
					)
				}

				// 날짜 무한 스크롤 구현
				(recyclerView.layoutManager as? LinearLayoutManager)?.let {
					val visibleItemCount = it.childCount
					val totalItemCount = it.itemCount

					val lastPosition = it.findLastVisibleItemPosition()

					when {
						lastPosition < visibleItemCount + 2 -> viewModel.addPreviousDateList()

						totalItemCount - lastPosition + 1 < visibleItemCount -> viewModel.addAfterDateList()
					}
				}
			}
		})
	}
}