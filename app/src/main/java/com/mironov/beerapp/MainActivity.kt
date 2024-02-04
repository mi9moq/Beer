package com.mironov.beerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mironov.beerapp.ui.beers.BeersScreen
import com.mironov.beerapp.ui.theme.BeerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeerAppTheme {
                BeersScreen()
            }
        }
    }
}