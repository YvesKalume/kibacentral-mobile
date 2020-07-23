package com.yveskalumenoble.kibacentral.view.activities

import android.content.DialogInterface
import android.content.Intent
import com.yveskalumenoble.kibacentral.databinding.ActivitySplashScreenBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.concurrent.schedule

class SplashScreenActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySplashScreenBinding.inflate(layoutInflater) }

    val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firebaseAuth.signInAnonymously()
            .addOnCompleteListener(this) {
                if (it.isSuccessful){

                    Timer().schedule(2000){
                        val intent = Intent(applicationContext,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Erreur d'auhentification")
                    builder.setMessage("Une erreur est survenue lors de l'authentification. " +
                            "Veuillez verifier votre connexion internet et réessayez")
                    builder.setPositiveButton("Réeassayer",DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(applicationContext,"Try again",Toast.LENGTH_SHORT).show()
                    })

                    builder.setNegativeButton("Fermer",DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(applicationContext,"Quitter",Toast.LENGTH_SHORT).show()
                    })
                    builder.show()

                }
            }
    }
}
