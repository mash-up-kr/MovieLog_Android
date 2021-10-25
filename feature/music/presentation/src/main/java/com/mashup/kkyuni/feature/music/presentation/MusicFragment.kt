package com.mashup.kkyuni.feature.music.presentation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.music.presentation.databinding.FragmentMusicBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicFragment : BindingFragment<FragmentMusicBinding>(
    R.layout.fragment_music
) {

    private val musicViewModel: MusicViewModel by viewModels()
    private val adapter by lazy { MusicAdapter(musicViewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                musicViewModel.videoList.collect {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun initView() {
        binding.recycler.run {
            setLayoutManager(LinearLayoutManager(context))
            adapter = adapter
        }
        binding.searchBar.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                musicViewModel.search(textView.text.toString())
            }
            true
        }
    }

}