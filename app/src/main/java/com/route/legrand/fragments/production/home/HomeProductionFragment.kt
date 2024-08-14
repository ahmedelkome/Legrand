package com.route.legrand.fragments.production.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.HomeFragmentProductionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeProductionFragment : BaseFragment<HomeFragmentProductionBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        observeLiveData()
        binding.injectionDataBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeProductionFragment_to_injectionFragment)
        }
    }

    override fun observeLiveData() {
    }


    override fun getLayout(): Int = R.layout.home_fragment_production
}