package com.mashup.kkyuni.feature.writing.presentation

import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingFragment: BindingFragment<FragmentWritingBinding>(R.layout.fragment_writing) {
    private val writingViewModel by viewModels<WritingViewModel>()
}