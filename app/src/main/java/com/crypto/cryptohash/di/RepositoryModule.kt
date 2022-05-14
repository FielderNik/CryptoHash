package com.crypto.cryptohash.di

import com.crypto.cryptohash.domain.repositories.CryptoRepository
import com.crypto.cryptohash.domain.repositories.CryptoRepositoryImpl
import com.crypto.cryptohash.domain.repositories.ExchangeRatesRepository
import com.crypto.cryptohash.domain.repositories.ExchangeRatesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCryptoRepository(cryptoRepositoryImpl: CryptoRepositoryImpl) : CryptoRepository

    @Binds
    abstract fun bindExchangeRatesRepository(exchangeRatesRepositoryImpl: ExchangeRatesRepositoryImpl) : ExchangeRatesRepository
}