package com.example.exoplayer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.exoplayer.databinding.ActVideoBinding

class ActVideo : AppCompatActivity() {

    private lateinit var mBinding: ActVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActVideoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
        setClickListener()
    }

    private fun initView() {}

    private fun setClickListener() {}
}