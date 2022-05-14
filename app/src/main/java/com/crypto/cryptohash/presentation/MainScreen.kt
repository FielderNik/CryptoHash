package com.crypto.cryptohash.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.crypto.cryptohash.presentation.hashrate.HashRateScreen

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            HashRateScreen()
        }
    }
}