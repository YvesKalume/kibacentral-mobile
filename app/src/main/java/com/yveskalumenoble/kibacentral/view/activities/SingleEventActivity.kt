package com.yveskalumenoble.kibacentral.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.databinding.ActivitySingleEventBinding
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.CONSTANT

class SingleEventActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySingleEventBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle = intent.extras
        val event : Event? = bundle?.getParcelable("event")

        binding.scheduleBtn.setOnClickListener {
            scheduleEvent(event!!)
        }
        binding.event = event
        Log.d("SingleEventActivity","le doc est ${event?.uid}")
    }

    fun scheduleEvent(event: Event){
        val firestore= FirebaseFirestore.getInstance()
        firestore.collection(CONSTANT.scheduledEvents)
            .add(event)
            .addOnCompleteListener {
            Toast.makeText(applicationContext,"Planifié",Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(applicationContext,"erreur ${it.message}",Toast.LENGTH_LONG).show()
            }
    }
}
