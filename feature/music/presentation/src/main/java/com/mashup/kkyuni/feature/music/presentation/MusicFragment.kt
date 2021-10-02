package com.mashup.kkyuni.feature.music.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.music.presentation.databinding.FragmentMusicBinding

class MusicFragment:BindingFragment<FragmentMusicBinding>(
    R.layout.fragment_music
) {
    private val musicViewModel: MusicViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}