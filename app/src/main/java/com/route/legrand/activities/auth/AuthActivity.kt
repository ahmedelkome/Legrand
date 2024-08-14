package com.route.legrand.activities.auth


import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.route.legrand.R
import com.route.legrand.activities.main.MainActivity
import com.route.legrand.base.BaseActivity
import com.route.legrand.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    private val authViewModel: AuthViewModel by viewModels<AuthViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding.lifecycleOwner = this
        binding.vm = authViewModel
        observeLiveData()
    }

    override fun observeLiveData() {
        authViewModel.loadingLiveData.observe(this) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        authViewModel.errorLiveData.observe(this) {
            showError(
                errorMessage = it
            )
        }
        authViewModel.event.observe(this) {
            when (it) {
                AuthEvents.navigateToMain -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.activity_auth

    override fun onPause() {
        super.onPause()
        hideLoading()
    }
}