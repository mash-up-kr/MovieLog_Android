package com.mashup.kkyuni.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.login.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    
    private val googleLoginActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            result.data?.let { viewModel.tryGoogleLogin(it) }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginSuccess.collect { isSuccess ->
                    if (isSuccess) {
                        LoginFragmentDirections.actionToCalendar().run {
                            findNavController().navigate(this)
                        }
                    }
                }
            }
        }

        viewModel.selectAccountIntentSender.observe(viewLifecycleOwner) {
            googleLoginActivityResultLauncher.launch(
                IntentSenderRequest.Builder(it).build()
            )
        }

        findNavController().navigate(R.id.navigation_writing)
    }
}