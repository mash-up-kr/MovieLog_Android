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
import com.google.android.material.snackbar.Snackbar
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
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
                with(viewModel) {
                    selectGoogleAccountState.collect { state ->
                        if (state is GoogleLoginState.Success) {
                            googleLoginActivityResultLauncher.launch(
                                state.data?.let { IntentSenderRequest.Builder(it).build() }
                            )
                        } else if (state is GoogleLoginState.Fail) {
                            showErrorSnackBar(state)
                        }
                    }
                    googleLoginState.collect { state ->
                        if (state is GoogleLoginState.Success) {
                            Log.d(javaClass.simpleName, "go to MainFragment")
                            //Todo: 메인 프래그먼트로 이동
                        } else if (state is GoogleLoginState.Fail) {
                            showErrorSnackBar(state)
                        }
                    }
                }
            }
        }
    }

    private fun showErrorSnackBar(state: GoogleLoginState.Fail) {
        state.errorMessage?.let {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }
}