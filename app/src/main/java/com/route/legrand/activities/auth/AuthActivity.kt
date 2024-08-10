package com.route.legrand.activities.auth

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.route.legrand.R
import com.route.legrand.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var biniding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        biniding = ActivityAuthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(biniding.root)
        initNavHost()
        val navGraph = navController.navInflater.inflate(R.navigation.auth_production_nav_graph)
        navController.graph = navGraph
    }

    private fun initNavHost() {
        val navHost = biniding.navHostProduction.getFragment<NavHostFragment>()
        navController = navHost.navController
    }
}