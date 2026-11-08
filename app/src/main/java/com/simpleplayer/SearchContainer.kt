package com.simpleplayer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchContainer(searchViewModel: SearchViewModel = viewModel()) {
    val uiState by searchViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.message) {
        val message = uiState.message

        if (message != null) {
            snackbarHostState.showSnackbar(message)
            searchViewModel.onMessageShow()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SearchScreen(
            uiState = uiState,
            onQueryChange = searchViewModel::onQueryChange,
            onSearch = searchViewModel::search,
            onReset = searchViewModel::resetSearch,
            onPlayClick = searchViewModel::onPlayClick
        )

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .safeDrawingPadding()
                .imePadding()
        )
    }
}
