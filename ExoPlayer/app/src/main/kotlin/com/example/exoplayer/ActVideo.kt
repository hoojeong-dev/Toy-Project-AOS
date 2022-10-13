package com.example.exoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exoplayer.databinding.ActVideoBinding

class ActVideo : AppCompatActivity() {

    private var mBinding = ActVideoBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        setClickListener()
    }

    private fun initView() {}

    private fun setClickListener() {}
}