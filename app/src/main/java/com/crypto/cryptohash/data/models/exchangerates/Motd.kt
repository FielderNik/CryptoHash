package com.crypto.cryptohash.data.models.exchangerates


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Motd(
    @SerialName("msg")
    val msg: String,
    @SerialName("url")
    val url: String
)