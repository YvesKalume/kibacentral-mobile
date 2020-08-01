package com.yveskalumenoble.kibacentral.view.activities

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import com.yveskalumenoble.kibacentral.databinding.ActivitySplashScreenBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashScreenBinding.inflate(layoutInflater) }

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(firebaseAuth.currentUser != null){
            Timer().schedule(1000){
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else {
            signInAnonymously()
        }
    }

    fun signInAnonymously (){
        firebaseAuth.signInAnonymously()
            .addOnCompleteListener{
                if (it.isSuccessful){
                    Timer().schedule(1000){
                        val intent = Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }

                else {
                    val builder = AlertDialog.Builder(this)
                    builder.setCancelable(false)
                    builder.setTitle("Erreur d'auhentification")
                    builder.setMessage("Une erreur est survenue lors de l'authentification. " +
                            "Veuillez vérifier votre connexion internet et réessayez")
                    builder.setPositiveButton("Réeassayer",DialogInterface.OnClickListener { dialogInterface, i ->
                        this.signInAnonymously()
                    })

                    builder.setNegativeButton("Quitter",DialogInterface.OnClickListener { dialogInterface, i ->
                        finish()
                    })
                    builder.show()
                }
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
