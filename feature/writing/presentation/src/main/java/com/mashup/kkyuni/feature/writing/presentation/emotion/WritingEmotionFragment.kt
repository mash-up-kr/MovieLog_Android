package com.mashup.kkyuni.feature.writing.presentation.emotion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingEmotionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingEmotionFragment: BindingFragment<FragmentWritingEmotionBinding>(R.layout.fragment_writing_emotion) {
    private val writingViewModel by viewModels<WritingViewModel> ({ requireParentFragment() })
    private val emotionViewModel by viewModels<WritingEmotionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emotionViewModel = emotionViewModel

        collectFlows()
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                emotionViewModel.nextEvent.collect {
                    writingViewModel.updateEmotion(it)

                    navigateToWritingMusic()
                }
            }
        }
    }

    private fun navigateToWritingMusic(){
        findNavController().navigate(R.id.writingMusicFragment)
    }
}