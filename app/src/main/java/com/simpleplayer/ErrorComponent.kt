package com.simpleplayer


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.simpleplayer.ui.theme.MusicTheme

@Composable
fun ErrorComponent(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(ScreenSizes.gap)
    ) {
        Box(modifier = Modifier.size(ScreenSizes.statusCircle)
                .background(color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.size(ScreenSizes.errorBadge)
                    .background(color = MaterialTheme.colorScheme.error,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "!", color = MaterialTheme.colorScheme.onError,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
        Text(
            text = stringResource(R.string.error_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.error_message),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onRetry
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
fun ErrorContentPreview() {
    MusicTheme {
        Surface {
            Column(modifier = Modifier.padding(ScreenSizes.gap)) {
                ErrorComponent(
                    onRetry = {}
                )
            }
        }
    }
}