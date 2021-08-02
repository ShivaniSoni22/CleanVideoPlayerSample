package com.sample.player.presentation.main

import androidx.activity.viewModels
import com.sample.player.databinding.ActivityMainBinding
import com.sample.player.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val viewModel: MainViewModel by viewModels()

}