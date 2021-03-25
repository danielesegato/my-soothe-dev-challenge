/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.danielesegato.androiddevchallenge.mysoothe.common

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntSize
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.coil.CoilImageDefaults
import com.google.accompanist.imageloading.DefaultRefetchOnSizeChangeLambda
import com.google.accompanist.imageloading.EmptyRequestCompleteLambda
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun CoilImagePalette(
    imageUrl: String,
    contentDescription: String?,
    onPaletteChange: (palette: Palette?) -> Unit,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null,
    fadeIn: Boolean = false,
    requestBuilder: (ImageRequest.Builder.(size: IntSize) -> ImageRequest.Builder)? = null,
    imageLoader: ImageLoader = CoilImageDefaults.defaultImageLoader(),
    shouldRefetchOnSizeChange: (currentResult: ImageLoadState, size: IntSize) -> Boolean = DefaultRefetchOnSizeChangeLambda,
    onRequestCompleted: (ImageLoadState) -> Unit = EmptyRequestCompleteLambda,
    error: @Composable (BoxScope.(ImageLoadState.Error) -> Unit)? = null,
    loading: @Composable (BoxScope.() -> Unit)? = null,
) {
    val context = LocalContext.current
    val imageData = remember {
        ImageRequest.Builder(context)
            .data(imageUrl)
            .allowHardware(false) // needed for palette
            .target(
                onStart = {
                    onPaletteChange(null)
                },
                onSuccess = { image ->
                    val bitmap = (image as BitmapDrawable).bitmap
                    Palette.Builder(bitmap)
                        .generate {
                            onPaletteChange(it)
                        }
                },
                onError = {
                    onPaletteChange(null)
                }
            )
            .build()
    }
    CoilImage(
        data = imageData,
        contentDescription = contentDescription,
        modifier = modifier,
        alignment = alignment,
        contentScale = contentScale,
        colorFilter = colorFilter,
        fadeIn = fadeIn,
        requestBuilder = requestBuilder,
        imageLoader = imageLoader,
        shouldRefetchOnSizeChange = shouldRefetchOnSizeChange,
        onRequestCompleted = onRequestCompleted,
        error = error,
        loading = loading,
    )
}
