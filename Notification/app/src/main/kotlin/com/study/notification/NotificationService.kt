package com.study.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.study.notification.MainActivity.Companion.ACTION_STOP

class NotificationService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent?.action != null && intent.action.equals(ACTION_STOP, ignoreCase = true)) {

            stopForeground(true)
            stopSelf()
        }

        getNotification()

        return START_STICKY
    }

    private fun getNotification() {

        // PendingIntent 를 사용하여, 알림 클릭 시 특정 activity 로 이동
        val intentMainLanding = Intent(this, MainActivity::class.java)
        val notifyPendingIntent =
            PendingIntent.getActivity(this, 0, intentMainLanding, PendingIntent.FLAG_MUTABLE)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        // 버전 26 이상부터 채널 생성 필수
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channelId="one-channel"
            val channelName="My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, channelId)
            builder.priority = NotificationCompat.PRIORITY_HIGH
        } else {
            builder = NotificationCompat.Builder(this)
        }

        // 알림 커스텀
        builder.run {
            setSmallIcon(R.drawable.small)
            setWhen(System.currentTimeMillis())
            setContentTitle("홍길동")
            setContentText("안녕하세요.")
            setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.big))
            setContentIntent(notifyPendingIntent)
        }

        // 답장 기능 구현
        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel = "답장"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }
        val replyIntent = Intent(this, ReplyReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(
            this, 30, replyIntent, PendingIntent.FLAG_MUTABLE
        )

        builder.addAction(
            NotificationCompat.Action.Builder(
                R.drawable.send,
                "답장",
                replyPendingIntent
            ).addRemoteInput(remoteInput).build()
        )

        manager.notify(11, builder.build())
    }
}