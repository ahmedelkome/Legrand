package com.route.legrand.fragments.production.injection

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.route.data.utils.shared.SharedPreferenceHelper
import com.route.domain.models.injection.InjectionData
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.FragmentInjectionBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InjectionFragment : BaseFragment<FragmentInjectionBinding>() {

    private val injectionViewModel: InjectionViewModel by viewModels<InjectionViewModel>()

    private var listOfPartNumber = mutableListOf<String>()

    @Inject
    lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = injectionViewModel
        injectionViewModel.getInjectionData()
        initEdit()
    }

    private fun initEdit() {
        binding.editOrAdd.setOnClickListener {
            showPasswordDialog()
        }
    }

    override fun observeLiveData() {
        injectionViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        injectionViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it)
        }
        injectionViewModel.listOfInjectionData.observe(viewLifecycleOwner) {
            Log.e("TAG", "observeLiveData: ${it}")
            bindListToMenu(it)
        }
    }

    private fun bindListToMenu(list: List<InjectionData>) {
        for (list in list.map { it.partNumber }) {
            if (list != null) {
                listOfPartNumber.add(list)
            }
        }
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_dropdown_item_1line,
            listOfPartNumber
        )
        binding.menuPartNum.setAdapter(adapter)
        binding.menuPartNum.setOnItemClickListener { parent, view, position, id ->
            val selectedPartNumber = parent.getItemAtPosition(position).toString()
            val selectPart = list.find { it.partNumber == selectedPartNumber }
            injectionViewModel.partNumber.value = selectedPartNumber
            selectPart?.let {
                binding.injection = it
            }

        }
    }

    fun showPasswordDialog() {
        // Create an EditText for password input
        val passwordEditText = EditText(requireActivity()).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            hint = "Enter Password"
        }

        // Build the AlertDialog
        val dialog = AlertDialog.Builder(requireActivity())
            .setTitle("Password Required")
            .setMessage("Please enter your password to proceed.")
            .setView(passwordEditText) // Set the EditText as the dialog view
            .setPositiveButton("OK") { dialog, _ ->
                // Get the entered password
                val password = passwordEditText.text.toString()
                // Invoke the callback function with the entered password
                if (password == "0123456789"){
                    Toast.makeText(requireActivity(),"Password Correct ",Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }else{
                    Toast.makeText(requireActivity(),"InCorrect Password",Toast.LENGTH_LONG).show()
                }

            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss() // Just dismiss the dialog
            }
            .create()

        // Show the dialog
        dialog.show()
    }


    override fun getLayout(): Int = R.layout.fragment_injection
}