package com.mironov.beerapp.ui.random

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.presentation.random.RandomScreenState.Content
import com.mironov.beerapp.presentation.random.RandomScreenState.Error
import com.mironov.beerapp.presentation.random.RandomScreenState.Initial
import com.mironov.beerapp.presentation.random.RandomScreenState.Loading
import com.mironov.beerapp.presentation.random.RandomViewModel
import com.mironov.beerapp.ui.utils.ErrorState
import org.koin.androidx.compose.koinViewModel

@Composable
fun RandomScreen() {
    val viewModel = koinViewModel<RandomViewModel>()
    val screenState = viewModel.state.collectAsState()

    when (val currentState = screenState.value) {
        Initial -> Unit
        Loading -> {
            LoadingState()
        }

        is Content -> ContentState(beer = currentState.content)
        is Error -> ErrorState(errorType = currentState.errorType) {
            viewModel.getRandomBeer()
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ContentState(
    beer: Beer,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()) //TODO() Возможно лучше переделать на LazyColumn
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = beer.name,
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = beer.tagline,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp),
                model = beer.imageUrl,
                contentDescription = null,
                alignment = Alignment.CenterStart,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                text = beer.description,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "ABV\n${beer.abv}%",
                textAlign = TextAlign.Center,
            )

            Text(
                text = "IBU\n${beer.ibu}",
                textAlign = TextAlign.Center,
            )

            Text(
                text = "EBC\n${beer.ebc}",
                textAlign = TextAlign.Center,
            )

            Text(
                text = "SRM\n${beer.srm}",
                textAlign = TextAlign.Center,
            )

            Text(
                text = "PH\n${beer.ph}",
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Best with:",
            textAlign = TextAlign.Center,
        )

        beer.foodPairing.forEach {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = it,
            )
        }
    }
}