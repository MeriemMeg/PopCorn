package com.example.meriemmeguellati.cinema.Notifications

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi

class JobReciever : BroadcastReceiver() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            SchedulerJob.scheduleJob(p0)
        }
    }


}