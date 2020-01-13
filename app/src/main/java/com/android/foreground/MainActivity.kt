package com.android.foreground

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.edit_text_input)
    }

    fun startService(v: View){
        val input = editText.text.toString()
        val serviceIntent = Intent(this,ExampleService::class.java)
        serviceIntent.putExtra("inputExtra",input)

        //startService(serviceIntent)
        //if we want our app to run a service while it is in the background we should call startForegroundService instead
        //but we have maximum 5s to call startForeground in the Service class

        //will do a check
        // if (Build.VERSION.SDK_INT >= 26) {
        //            context.startForegroundService(intent);
        //        } else {
        //            // Pre-O behavior.
        //            context.startService(intent);
        //        }
        ContextCompat.startForegroundService(this,serviceIntent) //we have maximum 5s to call startForeground in the service class before the os kills the service



    }
    fun stopService(v:View){
        val serviceIntent = Intent(this,ExampleService::class.java)
        stopService(serviceIntent)
    }
}
