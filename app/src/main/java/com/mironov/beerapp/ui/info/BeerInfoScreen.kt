package com.mironov.beerapp.ui.info

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mironov.beerapp.R
import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.presentation.info.BeerInfoScreenState
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Content
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Error
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Initial
import com.mironov.beerapp.presentation.info.BeerInfoScreenState.Loading
import com.mironov.beerapp.presentation.info.BeerInfoViewModel
import com.mironov.beerapp.ui.utils.ErrorState
import com.mironov.beerapp.ui.utils.LoadingState
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
        BeersScreenContent(
            screenState = screenState,
            tryingAgain = {
                viewModel.getById(id = id)
            }
        )
    }
}

@Composable
private fun BeersScreenContent(
    screenState: State<BeerInfoScreenState>,
    tryingAgain: () -> Unit,
) {
    when (val currentState = screenState.value) {

        Initial -> Unit

        Loading -> LoadingState()

        is Content -> ContentState(beer = currentState.content)

        is Error -> ErrorState(errorType = currentState.errorType) {
            tryingAgain()
        }
    }
}

@Composable
private fun ContentState(
    beer: Beer,
) {
    BeerInfo(beer = beer, modifier = Modifier)
}


@Composable
fun BeerInfo(beer: Beer, modifier: Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(8.dp),
    ) {

        BeerHeader(
            name = beer.name,
            tagline = beer.tagline
        )

        BeerDescription(
            imageUrl = beer.imageUrl,
            description = beer.description
        )

        Spacer(modifier = Modifier.height(16.dp))

        BeerCharacteristics(
            abv = beer.abv,
            ibu = beer.ibu,
            ebc = beer.ebc,
            srm = beer.srm,
            ph = beer.ph
        )

        Spacer(modifier = Modifier.height(16.dp))

        FoodParing(foodPairing = beer.foodPairing)
    }
}

@Composable
private fun BeerHeader(
    name: String,
    tagline: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = name,
        textAlign = TextAlign.Center,
    )

    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = tagline,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic,
    )
}

@Composable
private fun BeerDescription(
    imageUrl: String,
    description: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(8.dp),
            model = imageUrl,
            contentDescription = null,
            alignment = Alignment.CenterStart,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            text = description,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BeerCharacteristics(
    abv: Float,
    ibu: Float,
    ebc: Float,
    srm: Float,
    ph: Float,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "ABV\n${abv}%",
            textAlign = TextAlign.Center,
        )

        Text(
            text = "IBU\n${ibu}",
            textAlign = TextAlign.Center,
        )

        Text(
            text = "EBC\n${ebc}",
            textAlign = TextAlign.Center,
        )

        Text(
            text = "SRM\n${srm}",
            textAlign = TextAlign.Center,
        )

        Text(
            text = "PH\n${ph}",
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun FoodParing(foodPairing: List<String>) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = stringResource(R.string.best_with),
        textAlign = TextAlign.Center,
    )

    foodPairing.forEach {
        Text(
            modifier = Modifier
                .padding(top = 8.dp),
            text = it,
        )
    }
}