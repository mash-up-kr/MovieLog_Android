package com.mashup.kkyuni.feature.writing.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.domain.model.DiaryType
import com.mashup.kkyuni.feature.writing.domain.model.UploadState
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentPreviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingPreviewFragment: BindingFragment<FragmentPreviewBinding>(R.layout.fragment_preview) {

    private val writingViewModel by viewModels<WritingViewModel>({ requireParentFragment() })
    private val writingPreviewViewModel by viewModels<WritingPreviewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            viewModel = writingPreviewViewModel

            guideSwipe.setOnClickListener {
                it.isVisible = false
            }

            imageviewBack.setOnClickListener {
                findNavController().popBackStack(R.id.calendarFragment, true)
            }

            webviewPreview.run {
                settings.javaScriptEnabled = true
                lifecycleScope.launch {
                    loadUrl("https://deploy-preview-34--compassionate-wing-0abef6.netlify.app/preview")
                    delay(5000)
                    evaluateJavascript("setDiary(${writingViewModel.getCurrentWriting().toJson()})") {
                        progressBar.isVisible = false
                    }
                }
            }

            buttonConfirm.setOnClickListener {
                webviewPreview.evaluateJavascript("selectType()") { type ->
                    writingViewModel.run {
                        updateDiaryType(DiaryType.valueOf(type))
                        getCurrentWriting().run {
                            writingPreviewViewModel.requestUpload(this)
                        }
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    writingPreviewViewModel.uploading.collect {
                        if (it is UploadState.Complete) {
                            findNavController().popBackStack(R.id.calendarFragment, true)
                        }
                    }
                }
            }
        }
    }
}