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
import com.mashup.kkyuni.feature.writing.presentation.WritingViewModel
import com.mashup.kkyuni.feature.writing.presentation.databinding.FragmentWritingTitleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WritingTitleFragment :
    BindingFragment<FragmentWritingTitleBinding>(R.layout.fragment_writing_title) {
    private val writingViewModel by viewModels<WritingViewModel>({ requireParentFragment() })
    private val writingTitleViewModel by viewModels<WritingTitleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        collectFlows()
    }

    private fun initView() {
        setTitle(writingViewModel.getCurrentWriting().title ?: "")

        binding.run {
            titleViewModel = writingTitleViewModel

            edittextTitle.addTextChangedListener(object : TextWatcher {
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
                    if (editable.length > LIMIT_TITLE_LENGTH) {
                        editable.delete(inputStartIndex, inputStartIndex + inputCount)
                    }

                    setTitle(editable.toString())
                }
            })
        }
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                writingTitleViewModel.run {
                    launch {
                        backEvent.collect {
                            onBackPressed()
                        }
                    }

                    launch {
                        nextEvent.collect {
                            updateTitle(it)

                            navigateToWritingContent()
                        }
                    }
                }
            }
        }
    }

    // 작성 완료시 타이틀 업데이트
    private fun updateTitle(title: String) {
        writingViewModel.updateTitle(title)
    }

    // 이미 타이틀 작성이 되어 있다면 뷰 업데이트
    private fun setTitle(title: String) {
        writingTitleViewModel.setTitle(title)
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun navigateToWritingContent() {
        findNavController().navigate(R.id.contentFragment)
    }

    companion object {
        const val LIMIT_TITLE_LENGTH = 14
    }
}