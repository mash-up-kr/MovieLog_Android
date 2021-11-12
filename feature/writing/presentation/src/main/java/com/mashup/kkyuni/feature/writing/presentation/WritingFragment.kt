package com.mashup.kkyuni.feature.writing.presentation

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingFragment : BindingFragment<FragmentWritingBinding>(R.layout.fragment_writing) {
    private val writingViewModel by viewModels<WritingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyInputAdjustResize()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToEmotionFragment()
    }

    private fun navigateToEmotionFragment() {
        findNavController().navigate(R.id.action_to_writingEmotionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearInputAdjust()
    }

    private fun applyInputAdjustResize() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun clearInputAdjust() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED)
    }
}