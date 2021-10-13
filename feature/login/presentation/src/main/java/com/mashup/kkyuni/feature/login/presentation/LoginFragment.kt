package com.mashup.kkyuni.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private val googleLoginActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            result.data?.let { viewModel.onActivityResult(it) }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.kkyunibottombuttonLogin.setOnClickListener {
            viewModel.login(
                onSuccessListener = {
                    googleLoginActivityResultLauncher.launch(IntentSenderRequest.Builder(it).build())
                },
                onFailureListener = {
                    Log.e("LoginFragment", "${it.message}")
                }
            )
        }

        viewModel.run {
            googleLoginState.observe(viewLifecycleOwner) { state ->
                if (state == null) {
                    return@observe
                }
                when (state) {
                    is GoogleLoginState.Success -> {
                        viewModel.loginRequest()
                    }
                    is GoogleLoginState.Fail -> {
                        state.errorMessage?.let { message ->
                            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            isSuccess.observe(viewLifecycleOwner) {
                if (it) {
                    Log.d(javaClass.simpleName, "Login success. go to home")
                    //Todo: 홈 화면으로 넘어가기
                }
            }

            isLoading.observe(viewLifecycleOwner) {
                binding.progrssbarLogin.isVisible = it
            }
        }
    }
}