package com.simpleplayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import com.simpleplayer.ui.theme.ScreenSizes

enum class BottomTab(val route: NavKey, val label: Int, val icon: Int) {
    Search(TrackList, R.string.search, R.drawable.ic_search),
    LibraryTab(Library, R.string.library, R.drawable.ic_library_music),
    ProfileTab(Profile, R.string.profile, R.drawable.ic_person)
}

@Composable
fun BottomNavigationBar(
    selectedTab: NavKey,
    onSelectTab: (NavKey) -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        BottomTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab.route,
                onClick = { onSelectTab(tab.route) },
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(tab.label)) }
            )
        }
    }
}

@Composable
fun SecondaryTabScreen(title: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(ScreenSizes.gap),
        verticalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineSmall)
        Text(text = stringResource(R.string.demo_section))
    }
}
