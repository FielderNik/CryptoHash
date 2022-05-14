package com.crypto.cryptohash.domain.dto

data class UserProfit(
    val name: String,
    val userHashRate: Double,
    val exchangeRate: Double,

    val usdPrice: Double,
    val rubPrice: Double,

    val coinPerHour: Double,
    val usdPerHour: Double,
    val rubPerHour: Double,

    val coinPerDay: Double,
    val usdPerDay: Double,
    val rubPerDay: Double,

    val coinPerWeek: Double,
    val usdPerWeek: Double,
    val rubPerWeek: Double,

    val coinPerMonth: Double,
    val usdPerMonth: Double,
    val rubPerMonth: Double,

)
