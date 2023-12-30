package com.mironov.beerapp.ui.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType.CONNECTION
import com.mironov.beerapp.domain.entity.ErrorType.UNKNOWN
import com.mironov.beerapp.presentation.info.BeerInfoScreenState
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Content
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Error
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Initial
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Loading
import com.mironov.beerapp.presentation.info.BeerInfoViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeerInfoScreen(
    id: Long,
) {
    val viewModel = koinViewModel<BeerInfoViewModel>()
    val screenState = viewModel.state.collectAsState()
    viewModel.getById(id = id)

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        BeersScreenContent(screenState = screenState)
    }
}

@Composable
private fun BeersScreenContent(
    screenState: State<BeerInfoScreenState>,
) {
    when (val currentState = screenState.value) {

        Initial -> Unit

        Loading -> LoadingState()

        is Content -> ContentState(content = currentState.content)

        is Error -> {
            when (currentState.errorType) {
                CONNECTION -> TODO()
                UNKNOWN -> TODO()
            }
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ContentState(
    content: Beer,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Text(text = content.name)
        Text(text = content.tagline)

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(8.dp),
                model = content.imageUrl,
                contentDescription = null,
                alignment = Alignment.CenterStart,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                text = content.description,
                textAlign = TextAlign.Center,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = content.abv.toString(),
                textAlign = TextAlign.Center,
            )

            Text(
                text = content.ibu.toString(),
                textAlign = TextAlign.Center,
            )

            Text(
                text = content.ebc.toString(),
                textAlign = TextAlign.Center,
            )

            Text(
                text = content.srm.toString(),
                textAlign = TextAlign.Center,
            )

            Text(
                text = content.ph.toString(),
                textAlign = TextAlign.Center,
            )
        }

        content.foodPairing.forEach {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = it,
            )
        }
    }
}