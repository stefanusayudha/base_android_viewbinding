package com.iddevops.common.domain

import com.iddevops.common.domain.contract.ContentRepositoryContract
import com.iddevops.common.domain.contract.ContentUseCase
import com.iddevops.common.domain.contract.ProductRepositoryContract
import com.iddevops.common.domain.contract.ProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<ProductUseCase?> { getOrNull<ProductRepositoryContract>() }
    factory<ContentUseCase?> { getOrNull<ContentRepositoryContract>() }
}