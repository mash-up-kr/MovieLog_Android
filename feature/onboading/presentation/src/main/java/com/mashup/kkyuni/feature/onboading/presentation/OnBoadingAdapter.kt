package com.mashup.kkyuni.feature.onboading.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoadingAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return FirstOnBoadingFragment()
        } else if (position == 1) {
            return SecondOmBoadingFragment()
        } else {
            return ThirdOnBoadingFragment()
        }
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}