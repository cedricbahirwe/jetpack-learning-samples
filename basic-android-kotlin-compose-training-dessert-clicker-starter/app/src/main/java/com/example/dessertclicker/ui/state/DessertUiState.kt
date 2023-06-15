package com.example.dessertclicker.ui.state

data class DessertUiState(
    val revenue: Int = 0,
    var dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    var currentDessertPrice: Int = 0,
    var currentDessertImageId: Int = 0
)
