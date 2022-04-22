package com.iddevops.testdatabinding.presentation

import com.iddevops.common.presentation.activity.dashboard.DashboardDefaultChannel
import com.iddevops.core.common.presentation.base.BaseActivity
import com.iddevops.testdatabinding.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val dashboardDefaultChannel = registerForActivityResult(DashboardDefaultChannel()) {}

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initData() {

    }

    override fun initUI() {

    }

    override fun initAction() {

    }

    override fun initObserver() {
        dashboardDefaultChannel.launch(null)
    }
}