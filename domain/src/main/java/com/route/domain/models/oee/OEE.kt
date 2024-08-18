package com.route.domain.models.oee

import com.google.firebase.firestore.PropertyName

data class OEE(
    @PropertyName("Date")
    val Date: String? = null,
    @PropertyName("Time")
    val Time: String? = null,
    @PropertyName("Shift")
    val Shift: String? = null,
    @PropertyName("Machine")
    val Machine: String? = null,
    @PropertyName("Operator code")
    val OperatorCode: String? = null,
    @PropertyName("Part Number")
    val PartNumber: String? = null,
    @PropertyName("Cavity Number")
    val cavityNumber: String? = null,
    @PropertyName("Total Produced")
    val TotalProduced: String? = null,
    @PropertyName("Work Cav")
    val WorkCav: String? = null,
    @PropertyName("CT")
    val CT: String? = null,
    @PropertyName("E")
    val E: String? = null,
    @PropertyName("MO")
    val MO: String? = null,
    @PropertyName("H")
    val H: String? = null,
    @PropertyName("ME")
    val ME: String? = null,
    @PropertyName("Q")
    val Q: String? = null,
    @PropertyName("DM")
    val DM: String? = null,
    @PropertyName("CO")
    val CO: String? = null,
    @PropertyName("Su")
    val SU: String? = null,
    @PropertyName("CM")
    val CM: String? = null,
    @PropertyName("R")
    val R: String? = null,
    @PropertyName("WL")
    val WL: String? = null,
    @PropertyName("OTH")
    val OTH: String? = null,
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "partNumber" to PartNumber,
            "shift" to Shift,
            "cavityNumber" to cavityNumber,
            "date" to Date,
            "machine" to Machine,
            "cm" to CM,
            "co" to CO,
            "ct" to CT,
            "dm" to DM,
            "e" to E,
            "h" to H,
            "me" to ME,
            "mo" to MO,
            "operatorCode" to OperatorCode,
            "oth" to OTH,
            "q" to Q,
            "r" to R,
            "su" to SU,
            "time" to Time,
            "totalProduced" to TotalProduced,
            "wl" to WL,
            "workCav" to WorkCav
        )
    }

    fun toMapExcel(): Map<String, Any?> {
        return mapOf(
            "date" to Date,
            "shift" to Shift,
            "machine" to Machine,
            "operatorCode" to OperatorCode,
            "partNumber" to PartNumber,
            "cavityNumber" to cavityNumber,
            "totalProduced" to TotalProduced,
            "workCav" to WorkCav,
            "ct" to CT,
            "e" to E,
            "mo" to MO,
            "h" to H,
            "me" to ME,
            "q" to Q,
            "dm" to DM,
            "co" to CO,
            "su" to SU,
            "cm" to CM,
            "r" to R,
            "wl" to WL,
            "oth" to OTH,
        )
    }
}
