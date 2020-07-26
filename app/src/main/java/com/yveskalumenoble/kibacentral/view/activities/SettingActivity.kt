package com.yveskalumenoble.kibacentral.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.databinding.ActivitySettingBinding
import com.yveskalumenoble.kibacentral.util.CONSTANT

class SettingActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySettingBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val actionbar = supportActionBar
        actionbar!!.title = "Paramètres"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val sharedRef = getSharedPreferences(CONSTANT.noficationPrefrence,0)


        binding.notificationSwitch.isChecked = sharedRef.getBoolean(CONSTANT.noficationPrefrence,false)

        binding.notificationSwitch.setOnClickListener {
            if (binding.notificationSwitch.isChecked){
                val editor = sharedRef.edit()
                editor.putBoolean(CONSTANT.noficationPrefrence,true)
                editor.apply()
                Toast.makeText(applicationContext,"Vos recevrez les notification pour cles evenemnt planifier",Toast.LENGTH_LONG)
                    .show()
            } else {

                val editor = sharedRef.edit()
                editor.putBoolean(CONSTANT.noficationPrefrence,false)
                editor.apply()

                Toast.makeText(this,"Notifications Désactivées",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
