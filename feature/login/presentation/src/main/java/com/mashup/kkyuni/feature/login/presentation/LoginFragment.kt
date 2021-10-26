package com.mashup.kkyuni.feature.login.presentation

import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
                    googleLoginState.collect { state ->
                        if (state is GoogleLoginState.Success<*>) {
                            when (val data = state.data) {
                                is String -> {
                                    Log.d(javaClass.simpleName, "go to MainFragment")
                                    //Todo: 메인 프래그먼트로 이동
                                }
                                is IntentSender -> {
                                    googleLoginActivityResultLauncher.launch(
                                        IntentSenderRequest.Builder(data).build()
                                    )
                                }
                            }
                        } else if (state is GoogleLoginState.Fail) {
                            state.errorMessage?.let {
                                Toast.makeText(binding.root.context, it, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}