package com.iddevops.core.common.data

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val BASE_URL: String = "BASE_URL"

/**
 * Common data module - Use this as default OkHTTP, this module automatically provide chucker interceptor for debugging purpose
 * @author stefanus.ayudha@gmail.com
 */
val commonDataModule: Module = module {
    single(named(BASE_URL)) { Secured.getBaseUrl() }

    single {
        ChuckerCollector(
            context = androidApplication(),
            // Toggles visibility of the notification
            showNotification = true,
            // Allows to customize the retention period of collected data
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
    }

    single {
        ChuckerInterceptor.Builder(androidApplication())
            // The previously created Collector
            .collector(get())
            // The max body content length in bytes, after this responses will be truncated.
            .maxContentLength(250_000L)
            // List of headers to replace with ** in the Chucker UI
            // .redactHeaders("Auth-Token", "Bearer")
            // Read the whole response body even when the client does not consume the response completely.
            // This is useful in case of parsing errors or when the response body
            // is closed before being read like in Retrofit with Void and Unit types.
            .alwaysReadResponseBody(true)
            // Use decoder when processing request and response bodies. When multiple decoders are installed they
            // are applied in an order they were added.
            // .addBodyDecoder(decoder)
            // Controls Android shortcut creation. Available in SNAPSHOTS versions only at the moment
            // .createShortcut(true)
            .build()
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ChuckerInterceptor>())
            .build()
    }
}
