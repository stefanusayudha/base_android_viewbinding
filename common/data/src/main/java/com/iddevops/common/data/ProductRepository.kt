package com.iddevops.common.data

import com.iddevops.common.data.entity.ShoesEntity
import com.iddevops.common.data.web.self.ProductApiUseCase
import com.iddevops.common.domain.contract.ProductRepoContract
import com.iddevops.common.domain.model.ShoesModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(
    val productApiUseCase: ProductApiUseCase?
) : ProductRepoContract {

    // FIXME: Sample only
    private val dummyData = listOf(1, 2, 3, 4, 5).map {
        ShoesEntity(
            "Name of product $it",
            "Image URL of product $it",
            "32",
            listOf("RED", "GREEN", "BLUE").shuffled()[0]
        )
    }

    override suspend fun getProduct(): Flow<List<ShoesModel>> {
        // FIXME: Sample only
        delay(5000)

        return flow {
            emit(dummyData)
        }
    }
}