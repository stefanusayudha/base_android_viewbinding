package com.iddevops.common.feature_dashboard

import com.iddevops.common.feature_dashboard.fragment.sample.SampleViewModel
import com.iddevops.common.feature_dashboard.fragment.sample.SampleViewModelUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<DashboardSharedViewModelUseCase> { DashboardSharedViewModel(get()) }
    viewModel<SampleViewModelUseCase> { SampleViewModel(get()) }
}