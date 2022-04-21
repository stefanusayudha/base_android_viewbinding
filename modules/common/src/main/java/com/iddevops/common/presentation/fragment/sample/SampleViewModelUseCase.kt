package com.iddevops.common.presentation.fragment.sample

import com.iddevops.common.domain.model.ShoesModel
import com.iddevops.core.common.data.request.RequestState
import com.iddevops.core.common.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.StateFlow

// Abstract as view model contract is being used due to issue injecting view model in koin
abstract class SampleViewModelUseCase : BaseViewModel() {
    abstract val listProduct: StateFlow<RequestState<List<ShoesModel>>>
    abstract fun getProductList()
}