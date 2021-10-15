package com.mashup.kkyuni.feature.playlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.playlist.presentation.databinding.FragmentPlayListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayListFragment : BindingFragment<FragmentPlayListBinding>(R.layout.fragment_play_list) {
    private val playListViewModel: PlayListViewModel by viewModels()

    private val playListAdapter: PlayListAdapter by lazy { PlayListAdapter(playListViewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindingViewModel()
        observeLiveData()
    }

    private fun initView() {
        binding.recyclerViewPlayList.run {
            adapter = playListAdapter
        }
    }

    private fun bindingViewModel() {
        binding.run {
            this.viewModel = playListViewModel
        }
    }

    private fun observeLiveData() {
        playListViewModel.run {
            backLiveData.observe(viewLifecycleOwner) {
                // TODO back
            }
        }
    }
}