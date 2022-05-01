package com.iddevops.common.presentation.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.iddevops.common.databinding.FragmentDashboardBinding
import com.iddevops.core.common.presentation.base.BaseFragment

class DashboardFragment: BaseFragment<FragmentDashboardBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentDashboardBinding {
        return FragmentDashboardBinding.inflate(inflater, container, false)
    }

    override fun initData() {

    }

    override fun initUI() {

    }

    override fun initAction() {

    }

    override fun initObserver() {

    }
}