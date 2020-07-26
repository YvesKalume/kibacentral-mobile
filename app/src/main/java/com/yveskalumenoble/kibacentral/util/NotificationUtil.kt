package com.yveskalumenoble.kibacentral.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.yveskalumenoble.kibacentral.R
import com.yveskalumenoble.kibacentral.view.activities.MainActivity

fun NotificationManager.sendNotification(messageBody: String, context: Context){

    val intent = Intent(context,MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

    val ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    val builder = NotificationCompat.Builder(context,"kibacentral : êtes vous prêts ?")
        .setSmallIcon(R.drawable.logo)
        .setContentTitle("Kibacentral")
        .setContentText(messageBody)
        .setSound(ringtone)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(0,builder.build())

}