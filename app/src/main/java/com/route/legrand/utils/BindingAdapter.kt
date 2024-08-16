package com.route.legrand.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun BindingAdapterError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}

@BindingAdapter("valueDouble")
fun setValueDouble(editText: EditText, value: Double?) {
    val currentText = editText.text.toString()
    val newText = value?.toString() ?: ""
    if (currentText != newText) {
        editText.setText(newText)
    }
}

@InverseBindingAdapter(attribute = "valueDouble")
fun getValueDouble(editText: EditText): Double? {
    return editText.text.toString().toDoubleOrNull()
}

@BindingAdapter("valueDoubleAttrChanged")
fun setValueDoubleListener(editText: EditText, listener: InverseBindingListener?) {
    if (listener != null) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                listener.onChange()
            }
        })
    }
}

@BindingAdapter("valueInt")
fun setValueInt(editText: EditText, value: Int?) {
    val currentText = editText.text.toString()
    val newText = value?.toString() ?: ""
    if (currentText != newText) {
        editText.setText(newText)
    }
}

@InverseBindingAdapter(attribute = "valueInt")
fun getValueInt(editText: EditText): Int? {
    return editText.text.toString().toIntOrNull()
}

@BindingAdapter("valueIntAttrChanged")
fun setValueIntListener(editText: EditText, listener: InverseBindingListener?) {
    if (listener != null) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                listener.onChange()
            }
        })
    }
}