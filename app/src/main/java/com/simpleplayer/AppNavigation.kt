package com.simpleplayer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.BackNavigationBehavior
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.simpleplayer.ui.theme.ScreenSizes

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppNavigation(
    deepLinkStack: List<NavKey>? = null,
    onDeepLinkHandled: () -> Unit = {}
) {
    val backStack = rememberNavBackStack(TrackList)
    val adaptiveInfo = currentWindowAdaptiveInfoV2()
    val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(
        backNavigationBehavior = BackNavigationBehavior.PopLatest,
        directive = calculatePaneScaffoldDirective(adaptiveInfo)
    )

    LaunchedEffect(deepLinkStack) {
        if (deepLinkStack != null) {
            backStack.clear()
            backStack.addAll(deepLinkStack)
            onDeepLinkHandled()
        }
    }

    val goBack: () -> Unit = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    val currentRoute = backStack.last()
    val selectedTab = when (currentRoute) {
        Library -> Library
        Profile -> Profile
        else -> TrackList
    }

    val entries = entryProvider {
        entry<TrackList>(
            metadata = ListDetailSceneStrategy.listPane(
                detailPlaceholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                            .padding(ScreenSizes.gap),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.select_track),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        ) {
            SearchContainer(
                onOpenTrack = { trackId ->
                    if (backStack.lastOrNull() != TrackEdit(trackId)) {
                        while (backStack.lastOrNull().let { it is TrackDetail || it is TrackEdit }) {
                            backStack.removeLastOrNull()
                        }
                        backStack.add(TrackDetail(trackId = trackId))
                    }
                }
            )
        }

        entry<TrackDetail>(
            metadata = ListDetailSceneStrategy.detailPane()
        ) { route ->
            val tracks by TrackRepository.tracks.collectAsStateWithLifecycle()
            val track = tracks.find { track ->
                track.id == route.trackId
            }

            TrackDetailScreen(
                track = track,
                onBack = goBack,
                onEdit = {
                    backStack.add(TrackEdit(trackId = route.trackId))
                },
                modifier = Modifier.safeDrawingPadding()
            )
        }

        entry<TrackEdit>(
            metadata = ListDetailSceneStrategy.detailPane()
        ) { route ->
            val track = TrackRepository.getTrackById(route.trackId)
            if (track == null) {
                Column(
                    modifier = Modifier.safeDrawingPadding()
                ) {
                    Text("Трек не найден")
                    TextButton(onClick = goBack) {
                        Text("Назад")
                    }
                }
            } else {
                var title by rememberSaveable(route.trackId) {
                    mutableStateOf(track.title)
                }

                var artist by rememberSaveable(route.trackId) {
                    mutableStateOf(track.artist)
                }

                TrackEditScreen(
                    title = title,
                    artist = artist,
                    onTitleChange = { title = it },
                    onArtistChange = { artist = it },
                    onSave = {
                        if (title.isNotBlank() && artist.isNotBlank()) {
                            TrackRepository.updateTrack(
                                track.copy(
                                    title = title.trim(),
                                    artist = artist.trim()
                                )
                            )
                            goBack()
                        }
                    },
                    onBack = goBack,
                    modifier = Modifier.safeDrawingPadding()
                )
            }
        }
        entry<Library> {
            SecondaryTabScreen(title = stringResource(R.string.library))
        }

        entry<Profile> {
            SecondaryTabScreen(title = stringResource(R.string.profile))
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (currentRoute !is TrackEdit) {
                BottomNavigationBar(
                    selectedTab = selectedTab,
                    onSelectTab = { tab ->
                        if (currentRoute != tab) {
                            while (backStack.size > 1) {
                                backStack.removeLastOrNull()
                            }

                            if (tab != TrackList) {
                                backStack.add(tab)
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            backStack = backStack,
            onBack = goBack,
            sceneStrategies = listOf(listDetailStrategy),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entries
        )
    }
}