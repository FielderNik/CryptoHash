package com.crypto.cryptohash.domain.repositories

import com.crypto.cryptohash.core.Either
import com.crypto.cryptohash.core.toLeft
import com.crypto.cryptohash.core.toRight
import com.crypto.cryptohash.data.models.CoinsItem
import com.crypto.cryptohash.data.remote.CryptoService
import javax.inject.Inject

interface CryptoRepository {
    suspend fun getCoinInfo() : Either<Exception, CoinsItem>
}

class CryptoRepositoryImpl @Inject constructor(
    private val service: CryptoService
) : CryptoRepository {

    override suspend fun getCoinInfo(): Either<Exception, CoinsItem> {
        return try {
            service.getCoinItem("ETH")[0].toRight()
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.toLeft()
        }

    }

}