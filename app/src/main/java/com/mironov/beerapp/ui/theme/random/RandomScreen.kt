package com.mironov.beerapp.ui.theme.random

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mironov.beerapp.presentation.random.RandomScreenState.Content
import com.mironov.beerapp.presentation.random.RandomScreenState.Error
import com.mironov.beerapp.presentation.random.RandomScreenState.Initial
import com.mironov.beerapp.presentation.random.RandomScreenState.Loading
import com.mironov.beerapp.presentation.random.RandomViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RandomScreen() {
    val viewModel = koinViewModel<RandomViewModel>()
    val screenState = viewModel.state.collectAsState()

    when (val currentState = screenState.value) {
        Initial -> Unit
        Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Content -> TODO()
        is Error -> TODO()
    }
}