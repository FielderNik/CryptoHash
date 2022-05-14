package com.crypto.cryptohash.di

import com.crypto.cryptohash.data.remote.CryptoApi
import com.crypto.cryptohash.data.remote.CryptoService
import com.crypto.cryptohash.data.remote.ExchangeApi
import com.crypto.cryptohash.data.remote.ExchangeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatasourceModule {

    @Provides
    fun provideMemesService(cryptoApi: CryptoApi) : CryptoService {
        return cryptoApi.service
    }

    @Provides
    fun provideExchangeRatesService(exchangeApi: ExchangeApi) : ExchangeService {
        return exchangeApi.service
    }
}