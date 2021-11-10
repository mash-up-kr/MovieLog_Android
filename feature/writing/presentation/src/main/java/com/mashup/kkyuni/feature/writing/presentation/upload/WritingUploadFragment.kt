package com.mashup.kkyuni.feature.writing.presentation.upload

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingUploadBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingUploadFragment: BindingFragment<FragmentWritingUploadBinding>(R.layout.fragment_writing_upload) {

    private val writingViewModel by viewModels<WritingViewModel>({ requireParentFragment() })
    private val writingUploadViewModel by viewModels<WritingUploadViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        binding.run {
            viewModel = writingUploadViewModel
        }

        with(writingViewModel.getCurrentWriting()){
            date?.split("-")?.let { (year, month, day) ->
                writingUploadViewModel.setDateString(year, month, day)
            }

            writingUploadViewModel.requestUpload(this)
        }
    }
}