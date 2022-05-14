package com.crypto.cryptohash.domain.repositories

import com.crypto.cryptohash.core.Either
import com.crypto.cryptohash.core.toLeft
import com.crypto.cryptohash.core.toRight
import com.crypto.cryptohash.data.remote.ExchangeService
import com.crypto.cryptohash.domain.dto.ExchangeRatesUsdRub
import javax.inject.Inject

interface ExchangeRatesRepository {
    suspend fun getExchangeRatesUsdRub() : Either<Exception, ExchangeRatesUsdRub>
}

class ExchangeRatesRepositoryImpl @Inject constructor(
    private val service: ExchangeService
) : ExchangeRatesRepository {

    override suspend fun getExchangeRatesUsdRub(): Either<Exception, ExchangeRatesUsdRub> {
        return try {
            val rates = service.getExchangeRate().result
            ExchangeRatesUsdRub(rates).toRight()
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.toLeft()
        }
    }

}