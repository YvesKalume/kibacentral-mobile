package com.yveskalumenoble.kibacentral.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.databinding.ActivitySingleEventBinding
import com.yveskalumenoble.kibacentral.model.Event

class SingleEventActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySingleEventBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle = intent.extras
        val event : Event? = bundle?.getParcelable("event")

        binding.scheduleBtn.setOnClickListener {
            Toast.makeText(this,"${event!!.title} a été planifié",Toast.LENGTH_SHORT)
                .show()
        }
        binding.event = event
    }
}
