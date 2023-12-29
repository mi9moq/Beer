package com.mironov.beerapp.ui.beers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mironov.beerapp.R
import com.mironov.beerapp.domain.entity.Beer
import com.mironov.beerapp.domain.entity.ErrorType.CONNECTION
import com.mironov.beerapp.domain.entity.ErrorType.UNKNOWN
import com.mironov.beerapp.presentation.main.BeersScreenState
import com.mironov.beerapp.presentation.main.BeersScreenState.Content
import com.mironov.beerapp.presentation.main.BeersScreenState.Error
import com.mironov.beerapp.presentation.main.BeersScreenState.Initial
import com.mironov.beerapp.presentation.main.BeersScreenState.Loading
import com.mironov.beerapp.presentation.main.BeersViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeersScreen() {

    val viewModel = koinViewModel<BeersViewModel>()
    val screenState = viewModel.state.collectAsState(Initial)
    viewModel.getList()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = {
                        Icon(painterResource(R.drawable.ic_beers), contentDescription = null)
                    },
                    label = {
                        Text(text = "Пивы")
                    },
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = {
                        Icon(painterResource(R.drawable.ic_random), contentDescription = null)
                    },
                    label = {
                        Text(text = "Случайное пево")
                    },
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            BeersScreenContent(screenState = screenState)
        }
    }
}

@Composable
private fun BeersScreenContent(
    screenState: State<BeersScreenState>,
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
    content: List<Beer>,
) {
    LazyColumn {
        items(
            items = content,
            key = { it.id }
        ) { beer ->
            BeerCard(beer = beer)
        }
    }
}