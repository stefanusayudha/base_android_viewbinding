package com.iddevops.common.domain.contract

import com.iddevops.common.domain.model.ShoesModel
import kotlinx.coroutines.flow.Flow

interface ProductUseCase {
    suspend fun getProduct(): Flow<List<ShoesModel>>
}

interface ProductRepositoryContract: ProductUseCase