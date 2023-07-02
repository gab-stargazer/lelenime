package com.lelestacia.lelenime.core.network.di

import android.content.Context
import com.lelestacia.lelenime.core.network.BuildConfig
import com.lelestacia.lelenime.core.network.util.ConnectivityChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

    @Provides
    @Singleton
    @Named(ONLINE_INTERCEPTOR)
    fun provideOnlineInterceptor(): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        val maxAge = 60 * 60
        response
            .newBuilder()
            .header(CACHE_CONTROL, "public, max-age=$maxAge")
            .removeHeader(PRAGMA)
            .build()
    }

    @Provides
    @Singleton
    @Named(OFFLINE_INTERCEPTOR)
    fun provideOfflineInterceptor(@ApplicationContext mContext: Context): Interceptor =
        Interceptor { chain ->
            var request = chain.request()
            val connectivityChecker = ConnectivityChecker()
            val isConnectivityAvailable = connectivityChecker(mContext)
            if (!isConnectivityAvailable) {
                val maxStale = 60 * 60 * 24 * 7
                request = request
                    .newBuilder()
                    .header(CACHE_CONTROL, "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader(PRAGMA)
                    .build()
            }
            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        @Named(ONLINE_INTERCEPTOR) internetInterceptor: Interceptor,
        @Named(OFFLINE_INTERCEPTOR) offlineInterceptor: Interceptor,
        shaKey: CertificatePinner,
        @ApplicationContext mContext: Context
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .cache(Cache(mContext.cacheDir, CACHE_SIZE))
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(internetInterceptor)
            .addInterceptor(loggingInterceptor)
            .certificatePinner(shaKey)
            .build()

    private const val CACHE_SIZE: Long = (50 * 1024 * 1024).toLong()
    private const val ONLINE_INTERCEPTOR = "online_interceptor"
    private const val OFFLINE_INTERCEPTOR = "offline_interceptor"
    private const val CACHE_CONTROL = "Cache-Control"
    private const val PRAGMA = "Pragma"
}
