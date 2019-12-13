package com.example.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.sciencecafe.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.sciencecafe.R
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.content.ContextCompat.startActivity
import android.content.Intent
import com.example.api.ApiClient
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.api.AuthenticationInterceptor


class MainActivity : AppCompatActivity() {


    //    private var approvedEvents:MutableList<Event> =  mutableListOf<Event>()
    private lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.myNavHostFragment)

        val bottomNavigationView: BottomNavigationView = binding.bottomNavigationView

        NavigationUI.setupActionBarWithNavController(this,navController, drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        val pref = getPreferences(Context.MODE_PRIVATE)
        val jwt = pref!!.getString("token","")
        AuthenticationInterceptor.jwtToken = jwt!!
        if (pref!!.getBoolean("isLogin",false)) {
            val isAdmin = pref!!.getBoolean("isAdmin",false)
            val name = pref!!.getString("name","My profile")
            if (isAdmin) {
                    binding.navView.menu.findItem(R.id.loginFragment).setVisible(false)
                    binding.navView.menu.findItem(R.id.approve).setVisible(true)
                    binding.navView.menu.findItem(R.id.myEvents).setVisible(true)
                    binding.navView.menu.findItem(R.id.myRewards).setVisible(false)
                    binding.navView.menu.findItem(R.id.QRCode).setVisible(true)
                    binding.navView.menu.findItem(R.id.myProfile).setVisible(true)
                    binding.navView.menu.findItem(R.id.myProfile).setTitle(name)
                    binding.navView.menu.findItem(R.id.myProfile).setVisible(true)
            } else {
                    binding.navView.menu.findItem(R.id.loginFragment).setVisible(false)
                    binding.navView.menu.findItem(R.id.myEvents).setVisible(true)
                    binding.navView.menu.findItem(R.id.myRewards).setVisible(false)
                    binding.navView.menu.findItem(R.id.QRCode).setVisible(true)
                    binding.navView.menu.findItem(R.id.myProfile).setVisible(true)
                    binding.navView.menu.findItem(R.id.myProfile).setTitle(name)
                    binding.navView.menu.findItem(R.id.myProfile).setVisible(true)
            }
        } else {
                binding.navView.menu.findItem(R.id.approve).setVisible(false)
                binding.navView.menu.findItem(R.id.myEvents).setVisible(false)
                binding.navView.menu.findItem(R.id.myRewards).setVisible(false)
                binding.navView.menu.findItem(R.id.QRCode).setVisible(false)
                binding.navView.menu.findItem(R.id.myProfile).setVisible(false)
        }

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 0)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
