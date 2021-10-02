package com.mashup.kkyuni.feature.setting.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.setting.presentation.databinding.FragmentCreatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatorFragment : BindingFragment<FragmentCreatorBinding>(R.layout.fragment_creator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}