package com.yveskalumenoble.kibacentral.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.util.CONSTANT
import com.yveskalumenoble.kibacentral.util.sendNotification

class EventReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(context,NotificationManager::class.java)
                as NotificationManager

        val bundle = intent.extras
        val event = bundle?.getParcelable<Event>("event")
        val test = bundle?.getString("yves")
        Log.d("event intent", "$test")

        val preference = context.getSharedPreferences(CONSTANT.noficationPrefrence,Context.MODE_PRIVATE)
        val canNotify = preference.getBoolean(CONSTANT.noficationPrefrence,true)

        if (canNotify){
            notificationManager.sendNotification("${event?.title} c'est dans moins de 24 heures",context)
        }
    }

}