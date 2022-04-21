package com.iddevops.common.feature_dashboard.fragment.sample

import com.iddevops.common.domain.model.ShoesModel
import com.iddevops.core.common.base.BaseViewModel
import com.iddevops.core.common.request.RequestState
import kotlinx.coroutines.flow.StateFlow

// Abstract as view model contract is being used due to issue injecting view model in koin
abstract class SampleViewModelUseCase : BaseViewModel() {
    abstract val listProduct: StateFlow<RequestState<List<ShoesModel>>>
    abstract fun getProductList()
}