package com.crypto.cryptohash.data.remote

import com.crypto.cryptohash.data.models.exchangerates.ExchangeRates
import retrofit2.http.GET

interface ExchangeService {

    @GET("/convert?from=USD&to=RUB")
    suspend fun getExchangeRate(): ExchangeRates
}