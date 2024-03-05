package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.currencyconverter.ui.theme.CurrencyConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyConverterApp()
                }
            }
        }
    }
}

@Composable
fun CurrencyConverterApp() {
    var amount by remember { mutableStateOf("") }
    var sourceCurrency by remember { mutableStateOf("USD") }
    var targetCurrency by remember { mutableStateOf("EUR") }
    var convertedAmount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            CurrencyDropdown(
                currencies = listOf("USD", "EUR", "GBP", "JPY"),
                selectedCurrency = sourceCurrency,
                onCurrencySelected = { sourceCurrency = it }
            )
            Text(text = "->")
            CurrencyDropdown(
                currencies = listOf("USD", "EUR", "GBP", "JPY"),
                selectedCurrency = targetCurrency,
                onCurrencySelected = { targetCurrency = it }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { convertCurrency(amount, sourceCurrency, targetCurrency) }) {
            Text(text = "Convert")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = convertedAmount)
    }
}

@Composable
fun CurrencyDropdown(
    currencies: List<String>,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit
) {
    DropdownMenu(
        expanded = false,
        onDismissRequest = { },
    ) {
        currencies.forEach { currency ->
            DropdownMenuItem(
                onClick = {
                    onCurrencySelected(currency)
                }
            ) {
                Text(text = currency)
            }
        }
    }
}

fun convertCurrency(amount: String, sourceCurrency: String, targetCurrency: String) {
    // Implement currency conversion logic here
    // Use an API or a currency conversion library to perform the conversion
    // Update the convertedAmount variable with the result
    // For example:
    // val convertedAmount = performCurrencyConversion(amount.toDouble(), sourceCurrency, targetCurrency)
    // convertedAmount.toString()
}

@Preview(showBackground = true)
@Composable
fun CurrencyConverterPreview() {
    CurrencyConverterTheme {
        CurrencyConverterApp()
    }
}

