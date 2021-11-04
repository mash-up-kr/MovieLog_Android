package com.mashup.kkyuni.feature.writing.presentation.music

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
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingMusicBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingMusicFragment: BindingFragment<FragmentWritingMusicBinding>(R.layout.fragment_writing_music) {
    private val musicViewModel by viewModels<WritingMusicViewModel>()
    private val writingViewModel by viewModels<WritingViewModel>({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        collectFlows()
    }

    private fun initView() {
        binding.apply {
            musicViewModel = this@WritingMusicFragment.musicViewModel
            writingViewModel = this@WritingMusicFragment.writingViewModel
        }
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                musicViewModel.run {
                    launch {
                        backEvent.collect {
                            onBackPressed()
                        }
                    }

                    launch {
                        searchEvent.collect {
                            navigateToMusic()
                        }
                    }

                    launch {
                        nextEvent.collect {
                            navigateToTitle()
                        }
                    }
                }
            }
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun navigateToMusic() {
        findNavController().navigate(R.id.musicFragment)
    }

    private fun navigateToTitle() {
        findNavController().navigate(R.id.titleFragment)
    }
}