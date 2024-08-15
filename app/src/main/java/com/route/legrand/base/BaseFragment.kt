package com.route.legrand.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.route.legrand.R
import com.route.legrand.models.ErrorMessage

abstract class BaseFragment<DB : ViewDataBinding> : Fragment() {
    lateinit var binding: DB
    private var dialog: AlertDialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    open fun showLoading() {
        dialog = AlertDialog.Builder(requireActivity())
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
        val dialogError = AlertDialog.Builder(requireActivity())
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
        dialogError.create()
        dialogError.show()
    }

    abstract fun observeLiveData()

    abstract fun getLayout(): Int
}