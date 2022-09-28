package com.study.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.RemoteInput

class ReplyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val replyTxt = RemoteInput.getResultsFromIntent(intent)
            ?.getCharSequence("key_text_reply")

        val manager = context.getSystemService(
            AppCompatActivity.NOTIFICATION_SERVICE
        ) as NotificationManager

        manager.cancel(11)
    }
}