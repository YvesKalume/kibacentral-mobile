package com.yveskalumenoble.kibacentral.view.activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
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
import com.yveskalumenoble.kibacentral.receiver.EventReceiver
import com.yveskalumenoble.kibacentral.util.CONSTANT
import com.yveskalumenoble.kibacentral.viewmodel.EventViewModel
import java.util.*

class SingleEventActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySingleEventBinding.inflate(layoutInflater) }

    private lateinit var eventViewModel: EventViewModel

    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)

        setSupportActionBar(binding.toolbar)
        val actionbar = supportActionBar
        actionbar!!.setHomeAsUpIndicator(R.drawable.ic_chevron_left)
        actionbar.setDisplayShowTitleEnabled(false)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        val event : Event? = bundle?.getParcelable("event")

        binding.event = event

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        createNotificationChannel()

        eventViewModel.isScheduled(event!!).observe(this, Observer {
            if (it){
                eventViewModel.getParticipants(event).observe(this, Observer {
                    binding.scheduleBtn.text = "$it Participants"
                })

                binding.scheduleBtn.apply {
                    setBackgroundResource(R.drawable.disabled_btn_background)
                    setOnClickListener {
                        eventViewModel.removeScheduledEvent(event)
                    }
                }
            }
            else {

                binding.scheduleBtn.apply {
                    text = "J'Y vais"
                    setBackgroundColor(resources.getColor(R.color.colorPrimary))
                    setOnClickListener {
                        eventViewModel.scheduleEvent(event)
                        setAlarm(event)
                    }
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setAlarm(event: Event){
        val intent = Intent(this, EventReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val time = event.datetime!!.time - 3600
        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent)
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(
                CONSTANT.notificationChanelId,"Kibacentral Notification Channel"
                , NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Event Notification"
            val notificationManager = getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
