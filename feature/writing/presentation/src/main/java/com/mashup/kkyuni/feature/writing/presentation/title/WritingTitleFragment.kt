package com.mashup.kkyuni.feature.writing.presentation.title

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingTitleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingTitleFragment: BindingFragment<FragmentWritingTitleBinding>(R.layout.fragment_writing_title) {
    private val writingTitleViewModel by viewModels<WritingTitleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectFlows()
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                writingTitleViewModel.run {
                    backEvent.collect {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.titleViewModel = writingTitleViewModel

        binding.edittextTitle.addTextChangedListener(object : TextWatcher{
            var inputStartIndex: Int = 0
            var inputCount: Int = 0
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                inputStartIndex = start
                inputCount = count
            }

            override fun afterTextChanged(editable: Editable) {
                if(editable.length > 14){
                    editable.delete(inputStartIndex, inputStartIndex + inputCount)
                }
                writingTitleViewModel.updateTitle(editable.toString())
            }
        })
    }
}