package com.mashup.kkyuni.feature.writing.presentation.emotion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingEmotionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingEmotionFragment: BindingFragment<FragmentWritingEmotionBinding>(R.layout.fragment_writing_emotion) {
    private val writingViewModel by viewModels<WritingViewModel> ({ requireParentFragment() })
    private val emotionViewModel by viewModels<WritingEmotionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emotionViewModel = emotionViewModel
    }
}