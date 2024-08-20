package com.route.legrand.fragments.production.troubleshooting.details

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.route.legrand.R
import com.route.legrand.base.BaseActivity
import com.route.legrand.databinding.ActivityDetailsBinding
import com.route.legrand.fragments.production.troubleshooting.TypesTrouble
import com.route.legrand.fragments.production.troubleshooting.details.adapter.DetailsAdapter
import com.route.legrand.utils.ConstantsApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {

    private var adapter: DetailsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        initRecycler()
        getTypeOfIssue()
    }

    private fun initRecycler() {
        adapter = DetailsAdapter(listOf())
        binding.rvDetails.adapter = adapter
    }

    private fun getTypeOfIssue() {
        val name = intent.getStringExtra(ConstantsApp.ISSUE_NAME)
        val issueName = name?.let { TypesTrouble.valueOf(it) }
        when (issueName) {
            TypesTrouble.SINK_MARK -> {
                val imagesList = listOf(
                    R.drawable.sink_image_1,
                    R.drawable.sink_image_2
                )
                bindListToRecycler(imagesList)
                binding.imageSolve.setImageResource(R.drawable.sink_image_3)
            }

            TypesTrouble.EXCESSIVE -> {
                val listOfExcessive = listOf(
                    R.drawable.excessive_image_1,
                    R.drawable.excessive_image_2
                )
                bindListToRecycler(listOfExcessive)
                binding.imageSolve.setImageResource(R.drawable.excessive_image_3)
            }

            TypesTrouble.SHORT_SHOT -> {
                val listOfShort = listOf(
                    R.drawable.short_shot_1,
                    R.drawable.short_shot_2
                )
                bindListToRecycler(listOfShort)
                binding.imageSolve.setImageResource(R.drawable.short_shot_3)
            }

            TypesTrouble.PART_STUCK -> {
                val listOfPartStuck = listOf(
                    R.drawable.part_stuck_1,
                    R.drawable.part_stuck_2
                )
                bindListToRecycler(listOfPartStuck)
                binding.imageSolve.setImageResource(R.drawable.part_stuck_3)
            }

            TypesTrouble.RUNNER_STUCK -> {
                val listOfRunner = listOf(
                    R.drawable.runner_stuck_1,
                    R.drawable.runner_stuck_2
                )
                bindListToRecycler(listOfRunner)
                binding.imageSolve.setImageResource(R.drawable.runner_stuck_3)
            }

            TypesTrouble.BLACK_SPOTS -> {
                val listOfBlack = listOf(
                    R.drawable.black_spots_1,
                    R.drawable.black_spots_2,
                    R.drawable.black_spots_3,
                    R.drawable.black_spots_4,
                    R.drawable.black_spots_5,
                    R.drawable.black_spots_6,
                )
                bindListToRecycler(listOfBlack)
                binding.imageSolve.setImageResource(R.drawable.black_spots_issue)
            }

            TypesTrouble.BRITTLENESS_CRACKING -> {
                val listOfBrittleness = listOf(
                    R.drawable.cracking_1,
                    R.drawable.cracking_2,
                    R.drawable.cracking_3,
                    R.drawable.cracking_4
                )
                bindListToRecycler(listOfBrittleness)
                binding.imageSolve.setImageResource(R.drawable.cracking_issue)
            }

            TypesTrouble.BLISTERS -> {
                val listOfBlister = listOf(
                    R.drawable.blisters_1,
                    R.drawable.blisters_2
                )
                bindListToRecycler(listOfBlister)
                binding.imageSolve.setImageResource(R.drawable.blisters_issue)
            }

            TypesTrouble.BURN_MARKS -> {
                val listOfBurn = listOf(
                    R.drawable.burn_mark_1,
                    R.drawable.burn_mark_2
                )
                bindListToRecycler(listOfBurn)
                binding.imageSolve.setImageResource(R.drawable.burn_mark_issue)
            }

            TypesTrouble.DISCOLORATION -> {
                val listOfDiscoloration = listOf(
                    R.drawable.discoloration_1
                )
                bindListToRecycler(listOfDiscoloration)
                binding.imageSolve.setImageResource(R.drawable.discoloration_issue)
            }

            TypesTrouble.FLOW_MARK -> {
                val listOfFlow = listOf(
                    R.drawable.flow_mark_1,
                    R.drawable.flow_mark_2
                )
                bindListToRecycler(listOfFlow)
                binding.imageSolve.setImageResource(R.drawable.flow_mark_issue)
            }

            TypesTrouble.POOR_SURFACE -> {
                val listOfPoorSurface = listOf(
                    R.drawable.poor_surface_1
                )
                bindListToRecycler(listOfPoorSurface)
                binding.imageSolve.setImageResource(R.drawable.poor_surface_issue)
            }

            TypesTrouble.POOR_WELD -> {
                val listOfPoorWeld = listOf(
                    R.drawable.poor_weld_1,
                    R.drawable.poor_weld_2,
                    R.drawable.poor_weld_3,
                    R.drawable.poor_weld_4,
                    R.drawable.poor_weld_5,
                )
                bindListToRecycler(listOfPoorWeld)
                binding.imageSolve.setImageResource(R.drawable.poor_weld_issue)
            }

            TypesTrouble.SILVER_STREAKS -> {
                val listOfSilver = listOf(
                    R.drawable.silver_1,
                    R.drawable.silver_2,
                    R.drawable.silver_3,
                )
                bindListToRecycler(listOfSilver)
                binding.imageSolve.setImageResource(R.drawable.silver_issue)
            }

            TypesTrouble.WARPING -> {
                val listOfWarping = listOf(
                    R.drawable.warping_1,
                    R.drawable.warping_2,
                )
                bindListToRecycler(listOfWarping)
                binding.imageSolve.setImageResource(R.drawable.warping_issue)
            }

            TypesTrouble.VOIDS_BUBBLES -> {
                val listOfVoids = listOf(
                    R.drawable.void_1,
                    R.drawable.void_2,
                )
                bindListToRecycler(listOfVoids)
                binding.imageSolve.setImageResource(R.drawable.void_issue)
            }

            TypesTrouble.OVER_SIZED -> {
                binding.imageSolve.setImageResource(R.drawable.over_sized_issue)
            }

            null -> {
                Toast.makeText(this, "Images Not Founded", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun bindListToRecycler(list: List<Int>) {
        adapter?.updateListOfImages(list)
    }

    override fun observeLiveData() {

    }


    override fun getLayout(): Int = R.layout.activity_details
}