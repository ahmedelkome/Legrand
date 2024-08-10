package com.route.legrand.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.route.legrand.R
import com.route.legrand.models.ErrorMessage

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var binding: DB
    private var dialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        enableEdgeToEdge()
        binding.lifecycleOwner = this

    }

    open fun showLoading() {
        dialog = AlertDialog.Builder(this)
            .setView(R.layout.custom_loading)
            .create()
        dialog?.let {
            it.show()
        }
    }

    open fun hideLoading() {
        dialog?.let { it.dismiss() }
    }

    open fun showError(
        errorMessage: ErrorMessage
    ) {
        val dialogError = AlertDialog.Builder(this)
            .setTitle(errorMessage.title)
            .setMessage(errorMessage.message)
            .setPositiveButton(
                errorMessage.posTitle
            ) { dialog, which ->
                errorMessage.posClick?.let { it.invoke() }
            }
            .setNegativeButton(errorMessage.negTitle) { dialog, which ->
                errorMessage.negClick?.let { it.invoke() }
            }
    }

    abstract fun observeLiveData()
    
    abstract fun getLayout(): Int
}