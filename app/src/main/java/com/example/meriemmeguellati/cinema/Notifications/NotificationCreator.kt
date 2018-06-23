package com.example.meriemmeguellati.cinema.Notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.media.RingtoneManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.example.meriemmeguellati.cinema.Activities.FicheFilmActivity
import com.example.meriemmeguellati.cinema.Activities.MainActivity
import com.example.meriemmeguellati.cinema.Model.Film

class  NotificationCreator (context: Context,film : Film) {

    private val channelId = "channel-01"
    private val channelName = "SIL Channel"
    private val importance = NotificationManager.IMPORTANCE_HIGH
    private var mContext = context
    private var filmN = film


    @RequiresApi(Build.VERSION_CODES.M)
    fun CreateNotifClick() {
        // notification principale


        val testIntent = Intent(mContext, FicheFilmActivity::class.java)
        testIntent.putExtra("NotificationFilm",filmN)
        val pTestIntent = PendingIntent.getActivity(mContext, System.currentTimeMillis().toInt(), testIntent, 0)


        val notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(
                    channelId, channelName, importance)
            notificationManager.createNotificationChannel(mChannel)
        }

        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val noti = Notification.Builder(mContext)
                .setContentTitle("Nouveau film")
                .setContentText("Le film "+filmN.titre+" vient de sortir!")
                .setSmallIcon(android.R.drawable.btn_star)
                .setContentIntent(pTestIntent)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .build()

        notificationManager.notify(0, noti)
    }
}