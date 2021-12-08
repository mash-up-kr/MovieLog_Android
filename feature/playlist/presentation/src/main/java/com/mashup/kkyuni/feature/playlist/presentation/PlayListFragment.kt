package com.mashup.kkyuni.feature.playlist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.playlist.domain.model.Date
import com.mashup.kkyuni.feature.playlist.presentation.databinding.FragmentPlayListBinding
import com.mashup.kkyuni.feature.playlist.presentation.widget.ChoiceDateDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlayListFragment : BindingFragment<FragmentPlayListBinding>(R.layout.fragment_play_list) {
    private val playListViewModel: PlayListViewModel by viewModels()

    private val playListAdapter: PlayListAdapter by lazy { PlayListAdapter(playListViewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindingViewModel()
        observeLiveData()
        collectFlows()
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
                findNavController().run {
                    previousBackStackEntry?.savedStateHandle?.set("date", playListViewModel.getCurrentDate())

                    popBackStack(R.id.calendarFragment, false)
                }
            }
        }
    }

    private fun collectFlows(){
        playListViewModel.run {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                    changeDateEvent.collect {
                        ChoiceDateDialogFragment.showDialog(
                            childFragmentManager,
                            Date(it.year, it.month)
                        )
                    }
                }
            }
        }
    }
}