package com.crypto.cryptohash.presentation.hashrate

import androidx.lifecycle.viewModelScope
import com.crypto.cryptohash.core.onFailure
import com.crypto.cryptohash.core.onSuccess
import com.crypto.cryptohash.domain.usecases.GetUserProfitUseCase
import com.crypto.cryptohash.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HashRateViewModel @Inject constructor(
    private val getUserProfitUseCase: GetUserProfitUseCase
) : BaseViewModel<HashRateEvent, HashRateState, HashRateEffect>() {

    override val initState: HashRateState
        get() = HashRateState.Loading

    override fun handleEvent(event: HashRateEvent) {

        when (val currentState = state.value) {
            is HashRateState.Loading -> {
                reduce(currentState, event)
            }
            is HashRateState.Completed -> {
                reduce(currentState, event)
            }
            is HashRateState.Failed -> {
                reduce(currentState, event)
            }
        }
    }

    private fun reduce(currentState: HashRateState.Failed, event: HashRateEvent) {
        when(event) {
            HashRateEvent.EnterScreen -> {

            }
            HashRateEvent.Reload -> {
                loadHashRate()
            }
            is HashRateEvent.UpdateHashRate -> {

            }
        }
    }

    private fun reduce(currentState: HashRateState.Loading, event: HashRateEvent) {
        when (event) {
            HashRateEvent.EnterScreen -> {
                loadHashRate()
            }
            is HashRateEvent.UpdateHashRate -> {

            }
            HashRateEvent.Reload -> {

            }
        }
    }

    private fun reduce(currentState: HashRateState.Completed, event: HashRateEvent) {
        when (event) {
            HashRateEvent.EnterScreen -> {

            }
            is HashRateEvent.UpdateHashRate -> {
                if (event.userHashRate != null) {
                    loadHashRate(event.userHashRate)
                }
            }
            HashRateEvent.Reload -> {

            }
        }
    }

    private fun loadHashRate(userHashRate: Double = 0.0) {
        viewModelScope.launch {
            setState(HashRateState.Loading)

            withContext(Dispatchers.IO) {
                getUserProfitUseCase.run(GetUserProfitUseCase.Params(userHashRate))
            }
                .onFailure {
                    setState(HashRateState.Failed(it.message ?: "Что-то пошло не так"))
                }
                .onSuccess {
                    setState(HashRateState.Completed(it))
                }

        }
    }
}