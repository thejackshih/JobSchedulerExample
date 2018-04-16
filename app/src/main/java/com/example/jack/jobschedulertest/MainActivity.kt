package com.example.jack.jobschedulertest

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        var jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        var componentName = ComponentName(this, MyJobService::class.java)
        var job = JobInfo
                .Builder(1, componentName)
                .setMinimumLatency(1000)
                .setOverrideDeadline(1000)
                .build()
        jobScheduler.schedule(job)
        Log.d("Main", "JobScheduled")
    }
}

class MyJobService : JobService() {
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d("Service", "StopJob")
        return true
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("Service", "onStartJob")
        doSomeJob(params)
        return true
    }
    fun doSomeJob(params: JobParameters?) {
        Log.d("Service", "JobFinished")
        jobFinished(params, true)
    }
}
