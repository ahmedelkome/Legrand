package com.route.legrand.fragments.auth


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.AuthFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : BaseFragment<AuthFragmentBinding>() {
    private val authViewModel: AuthViewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = authViewModel
        observeLiveData()
    }

    override fun observeLiveData() {
        authViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        authViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(
                errorMessage = it
            )
        }
        authViewModel.event.observe(viewLifecycleOwner){
            when(it){
                AuthEvents.navigateToHome -> {
                    findNavController().navigate(R.id.action_authFragment_to_homeProductionFragment)
                    authViewModel.event.value = null
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.auth_fragment


}