package com.crypto.cryptohash.data.models.exchangerates


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRates(
    @SerialName("date")
    val date: String,
    @SerialName("historical")
    val historical: Boolean,
    @SerialName("info")
    val info: Info,
    @SerialName("motd")
    val motd: Motd,
    @SerialName("query")
    val query: Query,
    @SerialName("result")
    val result: Double,
    @SerialName("success")
    val success: Boolean
)