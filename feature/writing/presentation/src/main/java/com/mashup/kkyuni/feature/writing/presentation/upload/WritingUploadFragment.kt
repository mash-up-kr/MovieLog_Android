package com.mashup.kkyuni.feature.writing.presentation.upload

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingUploadBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingUploadFragment :
    BindingFragment<FragmentWritingUploadBinding>(R.layout.fragment_writing_upload) {

    private val writingViewModel by navGraphViewModels<WritingViewModel>(R.id.writing_graph)
    private val writingUploadViewModel by viewModels<WritingUploadViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.run {
            viewModel = writingUploadViewModel
        }

        writingViewModel.getCurrentWriting().date?.split("-")?.let { (year, month, day) ->
            writingUploadViewModel.setDateString(year, month, day)
        }

        lifecycleScope.launch {
            delay(500L)
            WritingUploadFragmentDirections.actionToPreviewFragment().run {
                findNavController().navigate(this)
            }
        }
    }
}