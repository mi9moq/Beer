package com.mironov.beerapp.ui.random

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mironov.beerapp.R
import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.presentation.random.RandomScreenState.Content
import com.mironov.beerapp.presentation.random.RandomScreenState.Error
import com.mironov.beerapp.presentation.random.RandomScreenState.Initial
import com.mironov.beerapp.presentation.random.RandomScreenState.Loading
import com.mironov.beerapp.presentation.random.RandomViewModel
import com.mironov.beerapp.ui.info.BeerInfo
import com.mironov.beerapp.ui.utils.ErrorState
import com.mironov.beerapp.ui.utils.LoadingState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RandomScreen(
    modifier: Modifier
) {
    val viewModel = koinViewModel<RandomViewModel>()
    val screenState = viewModel.state.collectAsState()

    when (val currentState = screenState.value) {
        Initial -> {
            viewModel.getRandomBeer()
        }

        Loading -> {
            LoadingState()
        }

        is Content -> ContentState(
            modifier = modifier,
            beer = currentState.content,
            nextBeer = viewModel::getRandomBeer
        )

        is Error -> ErrorState(
            errorType = currentState.errorType,
            tryAgain = viewModel::getRandomBeer
        )
    }
}

@Composable
private fun ContentState(
    modifier: Modifier,
    beer: Beer,
    nextBeer: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BeerInfo(beer = beer, Modifier.weight(1f))

        Button(
            onClick = { nextBeer() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(44.dp),
        ) {
            Text(text = stringResource(id = R.string.another))
        }
    }
}