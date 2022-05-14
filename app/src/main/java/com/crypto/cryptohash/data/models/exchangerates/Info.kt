package com.crypto.cryptohash.data.models.exchangerates


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("rate")
    val rate: Double
)