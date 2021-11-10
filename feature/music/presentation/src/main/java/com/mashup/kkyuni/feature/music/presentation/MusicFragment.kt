package com.mashup.kkyuni.feature.music.presentation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.music.presentation.databinding.FragmentMusicBinding
import com.mashup.kkyuni.feature.writing.domain.model.Music
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(
    R.layout.fragment_music
) {
    private val writingViewModel: WritingViewModel by viewModels ({ requireParentFragment() })
    private val musicViewModel: MusicViewModel by viewModels()
    private val musicAdapter by lazy { MusicAdapter(musicViewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                musicViewModel.run {
                    launch {
                        videoList.collect {
                            musicAdapter.submitList(it)
                        }
                    }

                    launch {
                        selectedVideo.collect {
                            writingViewModel.updateMusic(
                                Music(
                                    thumbnailUrl = it.snippet.thumbnails.medium.url,
                                    title = it.snippet.title,
                                    linkUrl = "www.youtube.com/watch?v=${it.id.videoId}",
                                    releaseDate = null,
                                    playTime = null
                                )
                            )
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.recycler.run {
            setLayoutManager(LinearLayoutManager(context))
            adapter = musicAdapter
        }
        binding.searchBar.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                musicViewModel.search(textView.text.toString())
            }
            true
        }
    }

}