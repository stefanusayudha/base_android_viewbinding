package com.iddevops.common.feature_dashboard

import com.iddevops.common.domain.contract.ProductUseCase

class DashboardSharedViewModel(
    private val productUseCase: ProductUseCase
): DashboardSharedViewModelUseCase() {

}