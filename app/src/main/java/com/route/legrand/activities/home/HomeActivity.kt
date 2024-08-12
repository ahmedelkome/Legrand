package com.route.legrand.activities.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.route.data.utils.Constants
import com.route.data.utils.shared.SharedPreferenceHelper
import com.route.legrand.R
import com.route.legrand.activities.auth.AuthActivity
import com.route.legrand.activities.main.MainActivity
import com.route.legrand.databinding.ActivityHomeBinding
import com.route.legrand.utils.ConstantsApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    @Inject lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    private lateinit var biniding: ActivityHomeBinding
    private var roleSelected: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biniding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(biniding.root)
        initListToAutoText()
    }


    private fun initListToAutoText() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            ConstantsApp.listOfRoles
        )
        biniding.roleMenu.setAdapter(adapter)
        biniding.roleMenu.setOnItemClickListener { parent, view, position, id ->
            val selectRole = parent.getItemAtPosition(position).toString()
            roleSelected = ConstantsApp.listOfRoles.find { it == selectRole }
            when (roleSelected) {
                ConstantsApp.PRODUCTION -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    sharedPreferenceHelper.saveRole(roleSelected!!)
                    Log.e("TAG", "save role ${sharedPreferenceHelper.getRole()}", )
                }

                ConstantsApp.TOOLSHOP -> {

                }

                ConstantsApp.QUALITY -> {

                }

                ConstantsApp.MAINTENANCE -> {

                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        sharedPreferenceHelper.clearValue(Constants.ROLE)
        Log.e("TAG", "onBackPressed: Clearrrrrrrrr", )
    }


}