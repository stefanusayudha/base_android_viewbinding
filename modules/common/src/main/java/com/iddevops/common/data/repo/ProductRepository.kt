package com.iddevops.common.data.repo

import com.iddevops.common.data.repo.web.self.ProductWebApi
import com.iddevops.common.domain.contract.ProductRepositoryContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepository(
    val productWebApi: ProductWebApi?
) : ProductRepositoryContract {

    // FIXME: Sample only
    private val dummyData = listOf(1, 2, 3, 4, 5).map {
        com.iddevops.common.data.entity.ShoesEntity(
            "Name of product $it",
            "Image URL of product $it",
            "32",
            listOf("RED", "GREEN", "BLUE").shuffled()[0]
        )
    }

    override suspend fun getProduct(): Flow<List<com.iddevops.common.domain.model.ShoesModel>> {
        // FIXME: Sample only
        delay(5000)

        return flow {
            emit(dummyData)
        }
    }
}