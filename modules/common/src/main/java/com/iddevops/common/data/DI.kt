package com.iddevops.common.data

import androidx.room.Room
import com.iddevops.common.data.repo.ContentRepository
import com.iddevops.common.data.repo.ProductRepository
import com.iddevops.common.data.repo.db.ContentDB
import com.iddevops.common.data.repo.db.ContentDBUseCase
import com.iddevops.common.data.repo.web.self.ContentWebApi
import com.iddevops.common.domain.contract.ContentRepositoryContract
import com.iddevops.common.domain.contract.ProductRepositoryContract
import com.iddevops.core.common.data.BASE_URL
import com.iddevops.core.common.data.createRetrofitService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {

    single {
        createRetrofitService(
            ContentWebApi::class.java,
            get(),
            get(named(BASE_URL))
        )
    }
    single<ContentDBUseCase> {
        Room.databaseBuilder(
            androidApplication(),
            ContentDB::class.java, ContentDB.DB_NAME
        ).build()
    }
    single<ContentRepositoryContract> { ContentRepository(getOrNull(), getOrNull()) }

    // FIXME: parse null to trigger sample data
    factory<ProductRepositoryContract> { ProductRepository(getOrNull()) }
}