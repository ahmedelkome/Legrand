package com.route.legrand.fragments.production.injection

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.route.domain.models.injection.InjectionData
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.FragmentInjectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InjectionFragment : BaseFragment<FragmentInjectionBinding>() {

    private val injectionViewModel: InjectionViewModel by viewModels<InjectionViewModel>()

    private var listOfPartNumber = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        injectionViewModel.getInjectionData()
    }

    override fun observeLiveData() {
        injectionViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it == true) {
                showLoading()
            }else{
                hideLoading()
            }
        }
        injectionViewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it)
        }
        injectionViewModel.listOfInjectionData.observe(viewLifecycleOwner) {
            Log.e("TAG", "observeLiveData: ${it}", )
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
            selectPart?.let {
                binding.injection = it
            }

        }
    }

    override fun getLayout(): Int = R.layout.fragment_injection
}