package com.mashup.kkyuni.feature.writing.presentation.emotion

import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingEmotionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingEmotionFragment: BindingFragment<FragmentWritingEmotionBinding>(R.layout.fragment_writing_emotion) {
    private val writingViewModel by viewModels<WritingViewModel> ({ requireParentFragment() })
}