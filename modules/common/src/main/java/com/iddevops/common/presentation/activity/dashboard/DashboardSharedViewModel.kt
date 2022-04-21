package com.iddevops.common.presentation.activity.dashboard

import com.iddevops.common.domain.contract.ProductUseCase

class DashboardSharedViewModel(
    private val productUseCase: ProductUseCase?
): DashboardSharedViewModelUseCase() {

}