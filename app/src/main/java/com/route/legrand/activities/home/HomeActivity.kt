package com.route.legrand.activities.home

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.route.legrand.databinding.ActivityHomeBinding
import com.route.legrand.activities.splash.SplashActivity
import com.route.legrand.utils.ConstantsApp

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        initListToAutoText()
    }

    private fun initListToAutoText() {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            ConstantsApp.listOfRoles
        )
        binding.roleMenu.setAdapter(adapter)
        binding.roleMenu.setOnItemClickListener { parent, view, position, id ->
            val selectRole = parent.getItemAtPosition(position).toString()
            val roleSelected = ConstantsApp.listOfRoles.find { it == selectRole }
            if (roleSelected == ConstantsApp.PRODUCTION) {

            } else if (roleSelected == ConstantsApp.TOOLSHOP) {

            } else if (roleSelected == ConstantsApp.MAINTENANCE) {

            } else if (roleSelected == ConstantsApp.QUALITY) {

            }
        }
    }
}