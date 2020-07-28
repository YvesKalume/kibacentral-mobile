package com.yveskalumenoble.kibacentral.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.firebase.auth.FirebaseAuth
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val firebaseAuth = FirebaseAuth.getInstance()
        Log.d("useruid","${firebaseAuth.currentUser?.uid}")

        setSupportActionBar(binding.toolbar)

        val navController = Navigation.findNavController(this,R.id.mainFragment)
        setUpBottomBar(navController)
    }


    private fun setUpBottomBar(navController: NavController){
        binding.bottomNavigation.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settingActivity -> startActivity(Intent(this,SettingActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}
