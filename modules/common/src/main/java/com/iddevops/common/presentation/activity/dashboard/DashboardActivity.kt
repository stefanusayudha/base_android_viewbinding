package com.iddevops.common.presentation.activity.dashboard

import com.iddevops.common.databinding.ActivityDashboardBinding
import com.iddevops.core.common.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun getViewBinding(): ActivityDashboardBinding {
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