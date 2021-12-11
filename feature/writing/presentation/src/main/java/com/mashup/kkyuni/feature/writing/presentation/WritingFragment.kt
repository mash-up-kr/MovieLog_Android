package com.mashup.kkyuni.feature.writing.presentation

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingFragment : BindingFragment<FragmentWritingBinding>(R.layout.fragment_writing) {
    private val writingViewModel by navGraphViewModels<WritingViewModel>(R.id.writing_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyInputAdjustResize()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        writingViewModel.run {
            initWriting()
            updateDate(arguments?.getString(KEY_DATE) ?: "")
        }
        navigateToEmotionFragment()
    }

    private fun navigateToEmotionFragment() {
        findNavController().navigate(R.id.action_to_writingEmotionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            "initView",
            ""
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        clearInputAdjust()
    }

    private fun applyInputAdjustResize() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun clearInputAdjust() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED)
    }

    companion object {
        const val KEY_DATE = "key_date"
    }
}