package com.mashup.kkyuni.feature.onboading.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.onboading.presentation.databinding.FragmentOnboadingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboadingFragment : BindingFragment<FragmentOnboadingBinding>(
    R.layout.fragment_onboading
){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = OnBoadingAdapter(this.requireActivity())
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.prev.visibility = View.INVISIBLE
                } else {
                    binding.prev.visibility = View.VISIBLE
                }

                if (position == 2) {
                    binding.next.text = "START!"
                    binding.next.setTextColor(getResources().getColor(R.color.green_ddf880))
                } else {
                    binding.next.text = "NEXT"
                    binding.next.setTextColor(getResources().getColor(R.color.white))
                }
            }
        })
        binding.next.setOnClickListener {
            if (binding.viewPager.currentItem < 2) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem+1, true)
            }
        }
        binding.prev.setOnClickListener {
            if (binding.viewPager.currentItem > 0) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem-1, true)
            }
        }
        binding.skip.setOnClickListener {
            OnboadingFragmentDirections.actionOnBoadingFragmentToCalendarFragment().run {
                findNavController().navigate(this)
            }
        }
    }
 }