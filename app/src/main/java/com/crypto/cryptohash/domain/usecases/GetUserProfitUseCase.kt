package com.crypto.cryptohash.domain.usecases

import com.crypto.cryptohash.core.Either
import com.crypto.cryptohash.core.flatMap
import com.crypto.cryptohash.core.toLeft
import com.crypto.cryptohash.core.toRight
import com.crypto.cryptohash.data.models.CoinsItem
import com.crypto.cryptohash.domain.dto.UserProfit
import com.crypto.cryptohash.domain.repositories.CryptoRepository
import com.crypto.cryptohash.domain.repositories.ExchangeRatesRepository
import javax.inject.Inject

class GetUserProfitUseCase @Inject constructor(
    private val cryptoRepository: CryptoRepository,
    private val exchangeRatesRepository: ExchangeRatesRepository
) {
    data class Params(
        val userHashRate: Double
    )

    suspend fun run(params: Params): Either<Exception, UserProfit> {
        return cryptoRepository.getCoinInfo().flatMap { coinItem ->
            exchangeRatesRepository.getExchangeRatesUsdRub().flatMap { usdRubRate ->

                calculateUserProfit(
                    coinsItem = coinItem,
                    exchangeRate = usdRubRate.exchangeRate,
                    params = params
                )
            }
        }
    }

    private suspend fun calculateUserProfit(
        coinsItem: CoinsItem,
        exchangeRate: Double,
        params: Params
    ): Either<Exception, UserProfit> {

        return try {
            val price = coinsItem.price
            val rubPrice = price * exchangeRate
            val userHashRateCalculate = params.userHashRate * 1000000.0
            val userRatio = userHashRateCalculate / coinsItem.networkHashrate
            val blockTime = coinsItem.difficulty / coinsItem.networkHashrate

            //profit per hour
            val blockPerHour = 3600 / blockTime
            val coinPerHour = coinsItem.rewardBlock * userRatio * blockPerHour
            val usdPerHour = coinPerHour * price
            val rubPerHour = usdPerHour * exchangeRate

            //profit per day
            val coinPerDay = coinPerHour * 24
            val usdPerDay = usdPerHour * 24
            val rubPerDay = rubPerHour * 24

            // profit per week
            val coinPerWeek = coinPerDay * 7
            val usdPerWeek = usdPerDay * 7
            val rubPerWeek = rubPerDay * 7

            // profit per month
            val coinPerMonth = coinPerDay * 30
            val usdPerMonth = usdPerDay * 30
            val rubPerMonth = rubPerDay * 30

            UserProfit(
                name = coinsItem.name,
                userHashRate = params.userHashRate,
                exchangeRate = exchangeRate,
                usdPrice = price,
                rubPrice = rubPrice,
                coinPerHour = coinPerHour,
                usdPerHour = usdPerHour,
                rubPerHour = rubPerHour,
                coinPerDay = coinPerDay,
                usdPerDay = usdPerDay,
                rubPerDay = rubPerDay,
                coinPerWeek = coinPerWeek,
                usdPerWeek = usdPerWeek,
                rubPerWeek = rubPerWeek,
                coinPerMonth = coinPerMonth,
                usdPerMonth = usdPerMonth,
                rubPerMonth = rubPerMonth
            ).toRight()
        } catch (ex: Exception) {
            ex.printStackTrace()
            ex.toLeft()
        }


    }
}