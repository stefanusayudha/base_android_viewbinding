package com.iddevops.common.data

import com.iddevops.common.domain.contract.ProductRepoContract
import org.koin.dsl.module

val dataModule = module {

    // FIXME: parse null to trigger sample data
    factory<ProductRepoContract> { ProductRepository(getOrNull()) }
}