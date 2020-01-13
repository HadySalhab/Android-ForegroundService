package com.android.foreground

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.android.foreground.App.Companion.CHANNEL_ID

class ExampleService: Service () {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    //called first time we create the service
    override fun onCreate() {
        super.onCreate()
    }


    //called everyTime startService() is called (passing a new Intent to it)
    //this will run on the main UI Thread
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        val input = intent?.getStringExtra("inputExtra")

        val notificationIntent = Intent(this,MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)

        val notification= NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Example Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(notificationPendingIntent)




            startForeground(1,notification.build()) //if we don't call this method and run the service without it , when our app is in the background , the system will kill our service



        //we can stop the service from inside by calling stopSelf()
                //stopSelf()

        return START_NOT_STICKY //FOREGROUND SERVICE WILL BARELY BE KILLED BY A PROCESS KILLING

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}