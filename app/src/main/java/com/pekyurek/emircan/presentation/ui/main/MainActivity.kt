package com.pekyurek.emircan.presentation.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.pekyurek.emircan.R
import com.pekyurek.emircan.databinding.ActivityMainBinding
import com.pekyurek.emircan.presentation.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResId: Int get() = R.layout.activity_main

    override val viewModel: MainViewModel by viewModels()

    override fun onInit(savedInstanceState: Bundle?) {
    }
}