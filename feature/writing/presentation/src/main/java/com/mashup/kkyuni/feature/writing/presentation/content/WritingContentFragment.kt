package com.mashup.kkyuni.feature.writing.presentation.content

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.writing.presentation.R
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingContentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingContentFragment :
    BindingFragment<FragmentWritingContentBinding>(R.layout.fragment_writing_content) {
    private val writingViewModel by navGraphViewModels<WritingViewModel>(R.id.writing_graph)
    private val writingContentViewModel by viewModels<WritingContentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectFlows()
    }

    private fun initView() {
        setContent(writingViewModel.getCurrentWriting().content ?: "")

        binding.run {
            contentViewModel = writingContentViewModel

            edittextContent.addTextChangedListener(object : TextWatcher {
                var inputStartIndex: Int = 0
                var inputCount: Int = 0
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    charSequence: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    inputStartIndex = start
                    inputCount = count
                }

                override fun afterTextChanged(editable: Editable) {
                    if (editable.length > LIMIT_CONTENT_LENGTH) {
                        editable.delete(inputStartIndex, inputStartIndex + inputCount)
                    }
                    setContent(editable.toString())
                }
            })
        }
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                writingContentViewModel.run {
                    launch {
                        backEvent.collect {
                            findNavController().popBackStack()
                        }
                    }

                    launch {
                        nextEvent.collect {
                            updateContent(it)

                            navigateToUpload()
                        }
                    }
                }
            }
        }
    }

    private fun setContent(content: String) {
        writingContentViewModel.setContent(content)
    }

    private fun updateContent(content: String) {
        writingViewModel.updateContent(content)
    }

    private fun navigateToUpload() {
        findNavController().navigate(R.id.uploadFragment)
    }

    companion object {
        const val LIMIT_CONTENT_LENGTH = 60
    }
}