package com.example.notesapp.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.notesapp.R

const val INTENT_COMMAND = "Command"
const val INTENT_COMMAND_EXIT = "Exit"
const val INTENT_COMMAND_REPLY = "Reply"
const val INTENT_COMMAND_ARCHIVE = "Achieve"

private const val NOTIFICATION_CHANNEL_GENERAL = "Checking"
private const val CODE_FOREGROUND_SERVICE = 1
private const val CODE_REPLY_INTENT = 2
private const val CODE_ACHIEVE_INTENT = 3



class MakeItService : Service (){

    var thing = "what ever!"

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val command = intent.getStringExtra(INTENT_COMMAND)
        val title = intent.getStringExtra("title")
        thing = "hi"
        if(command == INTENT_COMMAND_EXIT){
            stopService()
            return START_NOT_STICKY
        }

        if (title != null){
            showNotification(title)
        }
        else {
            showNotification("hola")
        }

        //showNotification(titulo = title!!)
        if(command == INTENT_COMMAND_REPLY){
            //Toast.makeText(this,"Clicked in Notifications", Toast.LENGTH_SHORT).show()
            stopService()
        }

        return START_STICKY
    }

    private fun stopService(){
        stopForeground(true)
        stopSelf()
    }

    private fun showNotification(titulo : String){
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val replyIntent = Intent(this,MakeItService::class.java).apply {
            putExtra(INTENT_COMMAND, INTENT_COMMAND_REPLY)
        }
        val achieveIntent = Intent(this,MakeItService::class.java).apply {
            putExtra(INTENT_COMMAND, INTENT_COMMAND_ARCHIVE)
        }
        val replyPendingIntent = PendingIntent.getService(
            this, CODE_REPLY_INTENT, replyIntent,0
        )
        val achievePendingIntent = PendingIntent.getService(
            this, CODE_ACHIEVE_INTENT, achieveIntent,0
        )

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            try {
                with(
                    NotificationChannel(
                        NOTIFICATION_CHANNEL_GENERAL,
                        "Make it Easy",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                ){
                    enableLights(false)
                    setShowBadge(false)
                    enableVibration(false)

                    lockscreenVisibility = Notification.VISIBILITY_PUBLIC
                    manager.createNotificationChannel(this)
                }
            }
            catch (e: Exception){
                Log.d("Error","shoNotification: ${e.message}")
            }

            with(
                NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_GENERAL)
            ){
                setTicker(null)
                setContentTitle(titulo)
                setContentText("Recuerda realizar la tarea")
                setAutoCancel(true)
                setOngoing(true)
                setWhen(System.currentTimeMillis())
                setSmallIcon(R.drawable.ic_launcher_foreground)
                priority = Notification.PRIORITY_MAX
                setContentIntent(replyPendingIntent)
                addAction(
                    0,"REPLY",replyPendingIntent
                )
                addAction(
                    0,"ACHIEVE",replyPendingIntent
                )
                startForeground(CODE_FOREGROUND_SERVICE,build())

            }
        }
    }
}