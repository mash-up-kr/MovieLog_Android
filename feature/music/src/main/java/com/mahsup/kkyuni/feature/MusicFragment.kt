package com.mahsup.kkyuni.feature

import android.os.Bundle
import android.view.View
import com.mahsup.kkyuni.R
import com.mahsup.kkyuni.databinding.FragmentMusicBinding
import com.mashup.kkyuni.core.BindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(R.layout.fragment_music) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}