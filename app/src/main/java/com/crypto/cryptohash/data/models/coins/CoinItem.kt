package com.crypto.cryptohash.data.models.coins


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinItem(
    @SerialName("algorithm")
    val algorithm: String? = null,
    @SerialName("coin")
    val coin: String? = null,
    @SerialName("difficulty")
    val difficulty: Long,
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String,
    @SerialName("network_hashrate")
    val networkHashrate: Long,
    @SerialName("price")
    val price: Double,
    @SerialName("reward")
    val reward: Double? = null,
    @SerialName("reward_block")
    val rewardBlock: Double,
    @SerialName("reward_unit")
    val rewardUnit: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("updated")
    val updated: Int? = null,
    @SerialName("volume")
    val volume: Double? = null
)