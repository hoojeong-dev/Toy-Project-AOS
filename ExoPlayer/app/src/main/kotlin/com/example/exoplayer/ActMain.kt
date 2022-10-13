package com.example.exoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exoplayer.databinding.ActMainBinding

class ActMain : AppCompatActivity() {

    private var mBinding = ActMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setClickListener()
    }

    private fun initView() {}

    private fun setClickListener() {}
}