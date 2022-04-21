package com.iddevops.common.presentation.fragment.sample

import com.iddevops.common.domain.contract.ProductUseCase
import com.iddevops.common.domain.model.ShoesModel
import com.iddevops.core.common.data.request.RequestData
import com.iddevops.core.common.data.request.asImmutable
import com.iddevops.core.common.data.request.loading
import kotlinx.coroutines.Job

class SampleViewModel(
    private val productUseCase: ProductUseCase?
) : SampleViewModelUseCase() {

    private var _listProductRequestJob: Job? = null
    private val _listProduct = RequestData<List<ShoesModel>>()
    override val listProduct get() = _listProduct.asImmutable()

    override fun getProductList() {
        _listProductRequestJob?.cancel()
        _listProduct.loading()
        _listProductRequestJob = requester(_listProduct) {
            productUseCase?.getProduct()
        }
    }
}