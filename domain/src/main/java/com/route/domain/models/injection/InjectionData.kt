package com.route.domain.models.injection

import com.google.firebase.firestore.PropertyName

data class InjectionData(
    @PropertyName("Part Number")
    val partNumber: String? = null,
    @PropertyName("Material Used")
    val materialUsed: String? = null,
    @PropertyName("Weight of 1 pc")
    val weight: Double? = null,
    @PropertyName("Cycle time")
    val cycleTime: Double? = null,
    @PropertyName("STD Cavities")
    val stdCavities: Int? = null,
    @PropertyName("Hourly Rate")
    val hourlyRate: Double? = null,
    @PropertyName("Quantity Per Quntainer")
    val quantityPer: Int? = null,
    @PropertyName("Mold Location")
    val moldLocation: String? = null,
    @PropertyName("Rack no")
    val rank: String? = null,
)
