package com.route.data.utils.shared

import android.content.Context
import android.content.SharedPreferences
import com.route.data.utils.Constants
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
){
        fun getRole(): String? {
            return sharedPreferences!!.getString(Constants.ROLE, null)
        }
        fun saveRole(role: String) {
            with(sharedPreferences!!.edit()) {
                putString(Constants.ROLE, role)
                apply()
            }
        }
    fun clearValue(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}