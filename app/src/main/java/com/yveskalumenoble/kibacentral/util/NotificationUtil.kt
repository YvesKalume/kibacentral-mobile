package com.yveskalumenoble.kibacentral.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.model.Event
import com.yveskalumenoble.kibacentral.view.activities.MainActivity
import com.yveskalumenoble.kibacentral.view.activities.SingleEventActivity

fun NotificationManager.sendNotification(messageBody: String,context: Context){

    val intent = Intent(context,MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

    val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val builder = NotificationCompat.Builder(context,CONSTANT.notificationChanelId)
        .setSmallIcon(R.drawable.logo)
        .setContentTitle("kibacentral : êtes vous prêts ?")
        .setContentText(messageBody)
        .setSound(ringtone)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(0,builder.build())

}