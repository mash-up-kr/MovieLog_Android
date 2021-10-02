package com.mashup.kkyuni.feature.setting.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.setting.presentation.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BindingFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    private val viewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
