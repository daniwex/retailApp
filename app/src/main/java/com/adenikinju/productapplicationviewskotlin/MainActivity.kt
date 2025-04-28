package com.adenikinju.productapplicationviewskotlin

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.adenikinju.productapplicationviewskotlin.databinding.ActivityMainBinding
import com.adenikinju.productapplicationviewskotlin.ui.viewmodel.RetailViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var retailViewModel: RetailViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        retailViewModel = ViewModelProvider(this)[RetailViewModel::class.java]


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_bottom_bar) as NavHostFragment
        val navController = navHostFragment.navController

        val navView: BottomNavigationView = findViewById(R.id.bottom_nav)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.filterFragment) {
                View.INVISIBLE
                activityMainBinding.bottomNav.apply {
                    animate()
                        .translationY(this.height.toFloat())
                        .alpha(0f)
                        .setDuration(300)
                        .start()
                }
            } else {
                activityMainBinding.bottomNav.apply {
                    View.VISIBLE
                        animate()
                            .translationY(0f)
                            .alpha(1f)
                            .setDuration(300)
                            .start()
                }
            }
        }

    }
}