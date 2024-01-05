package com.mironov.beerapp.ui.theme.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mironov.beerapp.R
import com.mironov.beerapp.domain.entity.ErrorType

@Composable
fun ErrorState(
    errorType: ErrorType,
    tryAgain: () -> Unit
) {
    val message = when (errorType) {
        ErrorType.CONNECTION -> stringResource(R.string.connection_error_message)
        ErrorType.UNKNOWN -> stringResource(R.string.unknown_error_message)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = message,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { tryAgain() },
        ) {
            Text(
                text = stringResource(id = R.string.try_again),
                textAlign = TextAlign.Center
            )
        }
    }

}