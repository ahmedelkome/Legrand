package com.route.legrand.activities.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.route.data.utils.shared.SharedPreferenceHelper
import com.route.legrand.R
import com.route.legrand.databinding.ActivityMainBinding
import com.route.legrand.utils.ConstantsApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var navGraph: NavGraph
    @Inject lateinit var sharedPreferenceHelper: SharedPreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        enableEdgeToEdge()
        initNavHost()
    }

    private fun initNavHost() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        initGraph()
    }

    private fun initGraph() {
        when(sharedPreferenceHelper.getRole()){

            ConstantsApp.PRODUCTION ->{
                navGraph =
                    navController.navInflater.inflate(R.navigation.production_nav_graph)
                navController.graph = navGraph
            }
        }
    }
}