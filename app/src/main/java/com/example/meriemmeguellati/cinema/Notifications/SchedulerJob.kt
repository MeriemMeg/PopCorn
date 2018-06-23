package com.example.meriemmeguellati.cinema.Notifications

import android.app.job.JobInfo
import android.content.Context

import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.Build
import android.support.annotation.RequiresApi

object SchedulerJob {

    // schedule the start of the service every 10 - 30 seconds
    @RequiresApi(api = Build.VERSION_CODES.M)
    fun scheduleJob(context: Context) {
        val serviceComponent = ComponentName(context, NotificationJobService::class.java!!)
        val builder = JobInfo.Builder(0, serviceComponent)
        builder.setMinimumLatency((170 * 1000).toLong()) // wait at least
        builder.setOverrideDeadline((400 * 1000).toLong()) // maximum delay
        //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
        //builder.setRequiresDeviceIdle(true); // device should be idle
        //builder.setRequiresCharging(false); // we don't care if the device is charging or not
        val jobScheduler = context.getSystemService(JobScheduler::class.java)
        jobScheduler!!.schedule(builder.build())
    }

}