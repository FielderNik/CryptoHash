package com.crypto.cryptohash.presentation.hashrate

import com.crypto.cryptohash.domain.dto.UserProfit

sealed class HashRateState {
    object Loading : HashRateState()
    class Completed(val userProfit: UserProfit) : HashRateState()
    class Failed(val message: String) : HashRateState()
}

sealed class HashRateEvent {
    object EnterScreen: HashRateEvent()
    class UpdateHashRate(val userHashRate: Double?) : HashRateEvent()
    object Reload: HashRateEvent()

}

sealed class HashRateEffect {

}