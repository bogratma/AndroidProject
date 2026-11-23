package com.simpleplayer

import android.content.Intent
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.deeplink.DeepLinkRequest
import androidx.navigation3.runtime.deeplink.DeepLinkUri
import androidx.navigation3.runtime.deeplink.UriDeepLinkMatcher
import androidx.navigation3.runtime.deeplink.withBackStack
import kotlinx.serialization.serializer

private val trackDeepLinkMatcher = UriDeepLinkMatcher(
    uriPattern = DeepLinkUri("https://simpleplayer.com/track/{trackId}"),
    serializer = serializer<TrackDetail>()
).withBackStack { match ->
    listOf(TrackList, match.key)
}

internal fun getDeepLinkStack(intent: Intent): List<NavKey>? {
    if (intent.action != Intent.ACTION_VIEW) return null
    val uri = intent.dataString ?: return null
    val request = DeepLinkRequest(uri = DeepLinkUri(uri))
    return trackDeepLinkMatcher.match(request)?.backStack
}
