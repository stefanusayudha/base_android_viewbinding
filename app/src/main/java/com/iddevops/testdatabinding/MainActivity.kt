package com.iddevops.testdatabinding

import com.iddevops.common.feature_dashboard.DashboardDefaultChannel
import com.iddevops.core.common.base.BaseActivity
import com.iddevops.testdatabinding.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    private val dashboardDefaultChannel = registerForActivityResult(DashboardDefaultChannel()) {}

    override fun initData() {

    }

    override fun initUi() {

    }

    override fun initObserver() {
        dashboardDefaultChannel.launch(null)
    }
}