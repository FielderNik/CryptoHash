package com.crypto.cryptohash.data.remote

import com.crypto.cryptohash.data.models.CoinsItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoService {
    @GET("/v2/coins?list=ETH")
    suspend fun getCoinItem(
        @Query("list")
        coin: String
    ): List<CoinsItem>
}