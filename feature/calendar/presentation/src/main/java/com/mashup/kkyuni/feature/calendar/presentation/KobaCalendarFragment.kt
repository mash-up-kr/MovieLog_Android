package com.mashup.kkyuni.feature.calendar.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
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
	}
}