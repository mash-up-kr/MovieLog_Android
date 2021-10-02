package com.mashup.kkyuni.feature.login.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.mashup.kkyuni.core.BindingFragment
import com.mashup.kkyuni.feature.login.domain.GoogleLoginState
import com.mashup.kkyuni.feature.login.presentation.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    companion object {
        private const val REQ_ONE_TAP = 2
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.kkyunibottombuttonLogin.setOnClickListener {
            viewModel.login(
                onSuccessListener = {
                    startIntentSenderForResult(
                        it, REQ_ONE_TAP,
                        null, 0, 0, 0, null
                    )
                },
                onFailureListener = {
                    Log.e("LoginFragment", "${it.message}")
                }
            )
        }

        viewModel.googleLoginState.observe(viewLifecycleOwner) {
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data == null) {
            Log.d(javaClass.simpleName, "data intent is null")
            return
        }

        when (requestCode) {
            REQ_ONE_TAP -> {
                viewModel.onActivityResult(data)
            }
        }
    }
}