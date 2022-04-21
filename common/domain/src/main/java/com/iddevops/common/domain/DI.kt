package com.iddevops.common.domain

import com.iddevops.common.domain.contract.ProductRepoContract
import com.iddevops.common.domain.contract.ProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<ProductUseCase> { get<ProductRepoContract>() }
}