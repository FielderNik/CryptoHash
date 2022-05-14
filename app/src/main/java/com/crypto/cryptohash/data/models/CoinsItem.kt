package com.crypto.cryptohash.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsItem(
    @SerialName("algorithm")
    val algorithm: String,
    @SerialName("coin")
    val coin: String,
    @SerialName("difficulty")
    val difficulty: Long,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("network_hashrate")
    val networkHashrate: Long,
    @SerialName("price")
    val price: Double,
    @SerialName("reward")
    val reward: Double,
    @SerialName("reward_block")
    val rewardBlock: Double,
    @SerialName("reward_unit")
    val rewardUnit: String,
    @SerialName("type")
    val type: String,
    @SerialName("updated")
    val updated: Int,
    @SerialName("volume")
    val volume: Double
)