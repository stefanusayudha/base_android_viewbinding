package com.iddevops.common.domain

import com.iddevops.common.domain.contract.ContentRepositoryContract
import com.iddevops.common.domain.contract.ContentUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<ContentUseCase?> { getOrNull<ContentRepositoryContract>() }
}