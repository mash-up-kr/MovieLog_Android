package com.mashup.kkyuni.feature.splash.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.splash.presentation.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment: BindingFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.login()

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.splashState.collect { state ->
                    when (state) {
                        is SplashState.NeedLogin -> {
                            startLoginFragment()
                        }
                        is SplashState.LoginSuccess -> {
                            SplashFragmentDirections.actionToCalendar().run {
                                findNavController().navigate(this)
                            }
                        }
                        is SplashState.Failure -> {
                            Toast.makeText(requireContext(), state.error.message, Toast.LENGTH_SHORT).show()
                            startLoginFragment()
                        }
                    }
                }
            }
        }
    }

    private fun startLoginFragment() {
        SplashFragmentDirections.actionToLogin().run {
            findNavController().navigate(this)
        }
    }
}