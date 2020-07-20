package com.yveskalumenoble.kibacentral.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.databinding.ActivitySingleEventBinding
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.model.UserEvent
import com.yveskalumenoble.kibacentral.util.CONSTANT
import com.yveskalumenoble.kibacentral.viewmodel.EventViewModel

class SingleEventActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySingleEventBinding.inflate(layoutInflater) }

    private lateinit var eventViewModel: EventViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        val bundle = intent.extras
        val event : Event? = bundle?.getParcelable("event")

        binding.event = event

        eventViewModel.isScheduled(event!!).observe(this, Observer {
            if (it){
                binding.scheduleBtn.apply {
                    text = "Deja Planifié"
                    setBackgroundResource(R.drawable.disabled_btn_background)
                }
            }
            else {

                binding.scheduleBtn.apply {
                    setOnClickListener {
                        eventViewModel.scheduleEvent(event)
                    }
                }
            }
        })


        //Log.d("SingleEventActivity","valeur est doc id: ${event?.uid}")

    }
}
