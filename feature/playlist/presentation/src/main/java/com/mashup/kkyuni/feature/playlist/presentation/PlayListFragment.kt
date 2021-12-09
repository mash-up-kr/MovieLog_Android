package com.mashup.kkyuni.feature.playlist.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
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

    private val backPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            onBackPressed()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addBackPressedCallback()
    }

    private fun addBackPressedCallback() {
        activity?.onBackPressedDispatcher?.addCallback(this, backPressedCallback)
    }

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
                onBackPressed()
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

    private fun onBackPressed() {
        findNavController().run {
            previousBackStackEntry?.savedStateHandle?.set("date", playListViewModel.getCurrentDate())

            popBackStack(R.id.calendarFragment, false)
        }
    }

    override fun onDetach() {
        super.onDetach()
        backPressedCallback.remove()
    }
}