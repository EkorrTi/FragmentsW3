package com.example.fragmentsw3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null
    private var isTablet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        isTablet = resources.getBoolean(R.bool.isTablet)

        if (isTablet) {
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container_1, ListContactsFragment())
                .replace(R.id.fragment_container_2, ContactFragment())
                .commit()
        } else {
            navController = (fragmentManager
                .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment)
                .navController
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (isTablet) navController!!.navigateUp() || super.onSupportNavigateUp()
        else super.onSupportNavigateUp()
    }
}