package com.mashup.kkyuni.feature.login.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
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
            googleLoginState.observe(viewLifecycleOwner) {
                if (it == null) {
                    return@observe
                }
                when (it) {
                    GoogleLoginState.Success -> {
                        viewModel.loginRequest()
                    }
                    GoogleLoginState.Canceled -> {
                        //Doing nothing
                    }
                    GoogleLoginState.NetworkError -> {
                        //Todo: 에러 토스트
                    }
                    else -> {
                        //Todo : 에러 토스트
                    }
                }
            }

            isSuccess.observe(viewLifecycleOwner) {
                if (it) {
                    Log.d(javaClass.simpleName, "Login success. go to home")
                    //Todo: 홈 화면으로 넘어가기
                }
            }
        }
    }
}