package com.route.legrand.fragments.production.troubleshooting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.route.legrand.R
import com.route.legrand.base.BaseFragment
import com.route.legrand.databinding.FragmentTroubleShootingBinding
import com.route.legrand.fragments.production.troubleshooting.details.DetailsActivity
import com.route.legrand.utils.ConstantsApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TroubleShootingFragment : BaseFragment<FragmentTroubleShootingBinding>() {

    private val troubleViewModel: TroubleViewModel by viewModels<TroubleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = troubleViewModel
    }

    override fun observeLiveData() {
        troubleViewModel.type.observe(viewLifecycleOwner) {
            when (it) {
                TypesTrouble.SINK_MARK -> {
                    navigateToDetails(it)
                }

                TypesTrouble.EXCESSIVE -> {
                    navigateToDetails(it)
                }
                TypesTrouble.SHORT_SHOT -> {
                    navigateToDetails(it)
                }
                TypesTrouble.PART_STUCK -> {
                    navigateToDetails(it)
                }
                TypesTrouble.RUNNER_STUCK -> {
                    navigateToDetails(it)
                }
                TypesTrouble.BLACK_SPOTS -> {
                    navigateToDetails(it)
                }
                TypesTrouble.BRITTLENESS_CRACKING -> {
                    navigateToDetails(it)
                }
                TypesTrouble.BLISTERS -> {
                    navigateToDetails(it)
                }
                TypesTrouble.BURN_MARKS -> {
                    navigateToDetails(it)
                }
                TypesTrouble.DISCOLORATION -> {
                    navigateToDetails(it)
                }
                TypesTrouble.FLOW_MARK -> {
                    navigateToDetails(it)
                }
                TypesTrouble.POOR_SURFACE -> {
                    navigateToDetails(it)
                }
                TypesTrouble.POOR_WELD -> {
                    navigateToDetails(it)
                }
                TypesTrouble.SILVER_STREAKS -> {
                    navigateToDetails(it)
                }
                TypesTrouble.WARPING -> {
                    navigateToDetails(it)
                }
                TypesTrouble.VOIDS_BUBBLES -> {
                    navigateToDetails(it)
                }
                TypesTrouble.OVER_SIZED -> {
                    navigateToDetails(it)
                }
            }
        }
    }

    private fun navigateToDetails(it: TypesTrouble) {
        val intent = Intent(requireActivity(), DetailsActivity::class.java)
        intent.putExtra(ConstantsApp.ISSUE_NAME, it.name)
        startActivity(intent)
    }

    override fun getLayout(): Int = R.layout.fragment_trouble_shooting

}