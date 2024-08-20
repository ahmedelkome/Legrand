package com.route.legrand.fragments.production.troubleshooting

import androidx.lifecycle.MutableLiveData
import com.route.legrand.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class TroubleViewModel : BaseViewModel() {

     val type = MutableLiveData<TypesTrouble>()


    fun sinkMark() {
        type.value = TypesTrouble.SINK_MARK
    }

    fun excessiveFlash(){
        type.value = TypesTrouble.EXCESSIVE
    }

    fun shortShot(){
        type.value = TypesTrouble.SHORT_SHOT
    }

    fun partStuck(){
        type.value = TypesTrouble.PART_STUCK
    }

    fun runnerStuck(){
        type.value = TypesTrouble.RUNNER_STUCK
    }

    fun blackSpots(){
        type.value = TypesTrouble.BLACK_SPOTS
    }

    fun cracking(){
        type.value = TypesTrouble.BRITTLENESS_CRACKING
    }

    fun blisters(){
        type.value = TypesTrouble.BLISTERS
    }

    fun burnMarks(){
        type.value = TypesTrouble.BURN_MARKS
    }

    fun discoloration(){
        type.value = TypesTrouble.DISCOLORATION
    }

    fun flowMark(){
        type.value = TypesTrouble.FLOW_MARK
    }

    fun poorSurface(){
        type.value = TypesTrouble.POOR_SURFACE
    }

    fun poorWeld(){
        type.value = TypesTrouble.POOR_WELD
    }

    fun silverStreaks(){
        type.value = TypesTrouble.SILVER_STREAKS
    }

    fun warping(){
        type.value = TypesTrouble.WARPING
    }

    fun voidsBubbles(){
        type.value = TypesTrouble.VOIDS_BUBBLES
    }

    fun overSized(){
        type.value = TypesTrouble.OVER_SIZED
    }
}