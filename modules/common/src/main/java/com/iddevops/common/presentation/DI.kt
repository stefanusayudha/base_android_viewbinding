package com.iddevops.common.feature_dashboard

import com.iddevops.common.presentation.activity.dashboard.DashboardSharedViewModel
import com.iddevops.common.presentation.activity.dashboard.DashboardSharedViewModelUseCase
import com.iddevops.common.presentation.fragment.sample.SampleViewModel
import com.iddevops.common.presentation.fragment.sample.SampleViewModelUseCase
import com.iddevops.common.presentation.fragment.todolist.TodoListViewModel
import com.iddevops.common.presentation.fragment.todolist.TodoListViewModelUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<DashboardSharedViewModelUseCase> {
        DashboardSharedViewModel(
            getOrNull()
        )
    }
    viewModel<SampleViewModelUseCase> {
        SampleViewModel(
            getOrNull()
        )
    }
    viewModel<TodoListViewModelUseCase> {
        TodoListViewModel(
            getOrNull()
        )
    }
}