package com.simpleplayer
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.simpleplayer.ui.theme.MusicTheme

@Composable
fun LoadingComponent(modifier: Modifier= Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
    ) {
        Text(
            text = stringResource(R.string.loading),
            style = MaterialTheme.typography.bodyMedium
        )
        repeat(5) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
            ) {
                Box(
                    modifier = Modifier.size(ScreenSizes.cover)
                        .background(color = MaterialTheme.colorScheme.surfaceContainer,
                            shape = MaterialTheme.shapes.small
                        )
                )
                Column(modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(ScreenSizes.smallGap)
                ) {
                    Box(modifier = Modifier.fillMaxWidth()
                            .height(ScreenSizes.skeletonLine)
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer,
                                shape = CircleShape
                            )
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(0.45f)
                            .height(ScreenSizes.skeletonLine)
                            .background(color = MaterialTheme.colorScheme.surfaceContainer,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}
@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
fun LoadingContentPreview() {
    MusicTheme {
        Surface {
            Column(
                modifier = Modifier.padding(
                    ScreenSizes.gap
                )
            ) {
                LoadingComponent()
            }
        }
    }
}