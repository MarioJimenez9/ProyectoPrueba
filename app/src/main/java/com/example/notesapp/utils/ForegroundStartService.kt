package com.example.notesapp.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.notesapp.service.INTENT_COMMAND
import com.example.notesapp.service.MakeItService

fun Context.foregroundStartService(command: String, msg : String){
    var coso = MakeItService()
    val intent = Intent(this, coso::class.java)
    if (command == "Start") {
        intent.putExtra(INTENT_COMMAND, command)
        intent.putExtra("title",msg)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.startForegroundService(intent)
        } else {
            this.startService(intent)
        }
    } else if (command == "Exit") {
        intent.putExtra(INTENT_COMMAND, command)

        this.stopService(intent)
    }
}