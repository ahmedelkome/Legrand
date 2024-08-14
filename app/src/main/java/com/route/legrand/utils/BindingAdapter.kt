package com.route.legrand.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun BindingAdapterError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}

