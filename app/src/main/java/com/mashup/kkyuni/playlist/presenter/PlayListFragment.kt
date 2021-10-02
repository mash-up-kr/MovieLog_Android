package com.mashup.kkyuni.playlist.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.R
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.databinding.FragmentPlayListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayListFragment: BindingFragment<FragmentPlayListBinding>(R.layout.fragment_play_list){
    private val playListViewModel: PlayListViewModel by viewModels()

    @Inject
    lateinit var playListAdapter: PlayListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initView()
        return view
    }

    private fun initView() {
        binding.recyclerViewPlayList.run {
            adapter = playListAdapter
        }
    }
}