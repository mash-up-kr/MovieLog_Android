package com.mashup.kkyuni.feature.onboading.presentation

import android.os.Bundle
import android.view.View
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.onboading.presentation.databinding.FragmentOnboadingBinding

class OnboadingFragment : BindingFragment<FragmentOnboadingBinding>(
    R.layout.fragment_onboading
){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = OnBoadingAdapter(this.requireActivity())
    }
}