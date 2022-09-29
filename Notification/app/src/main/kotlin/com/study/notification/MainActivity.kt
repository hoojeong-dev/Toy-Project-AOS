package com.study.notification

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import com.study.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        const val  ACTION_STOP = "${BuildConfig.APPLICATION_ID}.stop"
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /** Permission check */
    private fun checkForPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), packageName)
        return mode == AppOpsManager.MODE_ALLOWED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (!checkForPermission()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }

        setOnClickListener()
    }

    private fun setOnClickListener() {

        binding.button.setOnClickListener {

            startService(Intent(this, NotificationService::class.java))
        }
    }
}