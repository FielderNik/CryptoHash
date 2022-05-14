package com.crypto.cryptohash.data.models.exchangerates


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Query(
    @SerialName("amount")
    val amount: Int,
    @SerialName("from")
    val from: String,
    @SerialName("to")
    val to: String
)