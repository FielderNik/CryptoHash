package com.crypto.cryptohash.di

import com.crypto.cryptohash.domain.repositories.CryptoRepository
import com.crypto.cryptohash.domain.repositories.ExchangeRatesRepository
import com.crypto.cryptohash.domain.usecases.GetUserProfitUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetUserProfitUseCase(
        cryptoRepository: CryptoRepository,
        exchangeRatesRepository: ExchangeRatesRepository
    ): GetUserProfitUseCase {

        return GetUserProfitUseCase(
            cryptoRepository = cryptoRepository,
            exchangeRatesRepository = exchangeRatesRepository
        )
    }
}