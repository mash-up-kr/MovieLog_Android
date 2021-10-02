package com.mashup.kkyuni.feature.music.presentation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.music.presentation.databinding.FragmentMusicBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(
    R.layout.fragment_music
) {

    private val musicViewModel: MusicViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = MusicAdapter(musicViewModel)
        binding.searchBar.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                musicViewModel.search(textView.text.toString())
            }
            true
        }
    }
}