package com.mironov.beerapp.ui.beers

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun BeersScreen() {
    Scaffold(
        bottomBar = {

        }
    ) { paddingValues ->
        println(paddingValues)

        LazyColumn {

        }
    }
}