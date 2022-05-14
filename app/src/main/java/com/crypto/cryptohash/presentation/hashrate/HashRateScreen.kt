package com.crypto.cryptohash.presentation.hashrate

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crypto.cryptohash.R
import com.crypto.cryptohash.domain.dto.UserProfit

@Composable
fun HashRateScreen(
    viewModel: HashRateViewModel = hiltViewModel()
) {

    val screenState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendEvent(HashRateEvent.EnterScreen)
    }

    when (val state = screenState.value) {
        is HashRateState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is HashRateState.Completed -> {
            HashRateContent(viewModel, state.userProfit)
        }
        is HashRateState.Failed -> {
            FailedContent(viewModel, state.message)
        }
    }


}

@Composable
private fun FailedContent(viewModel: HashRateViewModel, message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Column() {
            Text(text = message)
            Button(onClick = {
                viewModel.sendEvent(HashRateEvent.Reload)
            }
            ) {
                Text(text = stringResource(id = R.string.button_reload))
            }
        }
    }
}

@Composable
private fun HashRateContent(
    viewModel: HashRateViewModel,
    userProfit: UserProfit
) {

    Column() {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            Text(text = String.format("%.2f $", userProfit.usdPrice))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "${userProfit.userHashRate} Mh/sec")
            Text(text = String.format("%.2f Р", userProfit.rubPrice))

        }
        Spacer(modifier = Modifier.height(16.dp))
        RateBlock(userProfit)
        Spacer(modifier = Modifier.height(32.dp))
        EnterRateBlock(viewModel)
    }
}

@Composable
private fun EnterRateBlock(viewModel: HashRateViewModel) {
    var rate by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Row(
        modifier = Modifier.fillMaxWidth().height(80.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = rate,
            onValueChange = { rateValue ->
                rate = rateValue
            },
            label = { Text(text = "HashRate") },
            placeholder = { Text(text = "Enter hashRate") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            modifier = Modifier.weight(1f).height(56.dp),
            onClick = {
                val userHashRate = rate.text.toDoubleOrNull()
                viewModel.sendEvent(HashRateEvent.UpdateHashRate(userHashRate))
            }
        ) {
            Text(text = stringResource(id = R.string.button_show_profit))
        }
    }
}

@Composable
private fun RateBlock(
    userProfit: UserProfit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RateCard(
                modifier = Modifier.weight(1f),
                period = stringResource(id = R.string.title_card_1_hour),
                coinRate = String.format("%.6f E", userProfit.coinPerHour),
                usdRate = String.format("%.2f $", userProfit.usdPerHour),
                rubRate = String.format("%.2f P", userProfit.rubPerHour)
            )
            RateCard(
                modifier = Modifier.weight(1f),
                period = stringResource(id = R.string.title_card_1_day),
                coinRate = String.format("%.6f E", userProfit.coinPerDay),
                usdRate = String.format("%.2f $", userProfit.usdPerDay),
                rubRate = String.format("%.2f P", userProfit.rubPerDay)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RateCard(
                modifier = Modifier.weight(1f),
                period = stringResource(id = R.string.title_card_1_week),
                coinRate = String.format("%.6f E", userProfit.coinPerWeek),
                usdRate = String.format("%.2f $", userProfit.usdPerWeek),
                rubRate = String.format("%.2f P", userProfit.rubPerWeek)
            )
            RateCard(
                modifier = Modifier.weight(1f),
                period = stringResource(id = R.string.title_card_1_month),
                coinRate = String.format("%.6f E", userProfit.coinPerMonth),
                usdRate = String.format("%.2f $", userProfit.usdPerMonth),
                rubRate = String.format("%.2f P", userProfit.rubPerMonth)
            )
        }

    }
}

@Composable
private fun RateCard(
    modifier: Modifier = Modifier,
    period: String,
    coinRate: String,
    usdRate: String,
    rubRate: String
) {
    Card(modifier = modifier.padding(2.dp), elevation = 8.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = period, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(4.dp))
            ProfitText(text = coinRate)
            ProfitText(text = usdRate)
            ProfitText(text = rubRate)
        }
    }
}

@Composable
private fun ProfitText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h2,
    )
}

//@Preview
//@Composable
//fun HashRateScreenPreview() {
//
//}


