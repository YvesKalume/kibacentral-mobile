package com.yveskalumenoble.kibacentral.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yveskalumenoble.kibacentral.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.googleSignInBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}
