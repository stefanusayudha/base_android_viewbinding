package com.iddevops.common.feature_dashboard

import com.iddevops.common.feature_dashboard.databinding.ActivityDashboardBinding
import com.iddevops.core.common.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun getBinding(): ActivityDashboardBinding {
        return ActivityDashboardBinding.inflate(layoutInflater)
    }

    private val shareVM by viewModel<DashboardSharedViewModelUseCase>()

    override fun initData() {

    }

    override fun initUi() {

    }

    override fun initObserver() {

    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}