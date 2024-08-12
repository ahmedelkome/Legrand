package com.route.legrand.activities.home

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.route.legrand.R
import com.route.legrand.databinding.ActivityHomeBinding
import com.route.legrand.utils.ConstantsApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var biniding: ActivityHomeBinding
    lateinit var navController: NavController
    private var roleSelected: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biniding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(biniding.root)
        initNavHost()
        initListToAutoText()
    }

    private fun initNavHost() {
        val navHost = biniding.navHostFragmentContainer.getFragment<NavHostFragment>()
        navController = navHost.navController
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
            if (roleSelected == ConstantsApp.PRODUCTION) {
                val navGraph =
                    navController.navInflater.inflate(R.navigation.auth_production_nav_graph)
                navController.graph = navGraph
                biniding.tilRole.isVisible = false
            } else if (roleSelected == ConstantsApp.TOOLSHOP) {

            } else if (roleSelected == ConstantsApp.MAINTENANCE) {

            } else if (roleSelected == ConstantsApp.QUALITY) {

            }
        }
    }
}