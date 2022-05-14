package com.crypto.cryptohash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Event, State, Effect> : ViewModel() {

    abstract val initState: State

    private val _state = MutableStateFlow<State>(initState)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>(replay = 0)
    val effect = _effect.asSharedFlow()



    fun sendEvent(event: Event) {
        handleEvent(event)
    }

    suspend fun setState(newState: State) {
        _state.emit(newState)
    }

    suspend fun sendEffect(newEffect: Effect) {
        _effect.emit(newEffect)
    }

    fun launchOnMain(block: suspend () -> Unit) : Job {
        return viewModelScope.launch(Dispatchers.Main) {
            block()
        }
    }

    suspend fun <T> withIo(block: suspend CoroutineScope.() -> T): T {
        return withContext(Dispatchers.IO){
            block()
        }
    }

    open fun handleEvent(event: Event) {

    }
}