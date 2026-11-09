package com.simpleplayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.simpleplayer.ui.theme.ScreenSizes

@Composable
fun TrackEditScreen(
    title: String,
    artist: String,
    onTitleChange: (String) -> Unit,
    onArtistChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(ScreenSizes.gap),
        verticalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
    ) {
        TextButton(onClick = onBack) {
            Text("Назад")
        }

        Text(
            text = "Редактирование трека",
            style = MaterialTheme.typography.headlineSmall
        )

        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Название") },
            singleLine = true
        )

        OutlinedTextField(
            value = artist,
            onValueChange = onArtistChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Исполнитель") },
            singleLine = true
        )

        Button(
            onClick = onSave,
            enabled = title.isNotBlank() && artist.isNotBlank()
        ) {
            Text("Сохранить")
        }
    }
}