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
package dev.danielesegato.androiddevchallenge.mysoothe.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import dev.danielesegato.androiddevchallenge.mysoothe.R

private val DarkColorPalette = darkColors(
    primary = whiteFull,
    secondary = rust300,
    background = gray900,
    surface = white150,
    onPrimary = gray900,
    onSecondary = gray900,
    onBackground = taupe100,
    onSurface = white800,
)

private val LightColorPalette = lightColors(
    primary = gray900,
    secondary = rust600,
    background = taupe100,
    surface = white850,
    onPrimary = whiteFull,
    onSecondary = whiteFull,
    onBackground = taupe800,
    onSurface = gray800,
)

data class MySootheDrawables(
    val isLight: Boolean,
    val logoImageVector: ImageVector,
    val welcomeBackgroundVector: ImageVector,
    val loginBackgroundVector: ImageVector,
)

private val LocalMySootheDrawables =
    staticCompositionLocalOf<MySootheDrawables> {
        error("no MySootheDrawable provided")
    }

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val themeDrawables = if (darkTheme) {
        MySootheDrawables(
            isLight = false,
            logoImageVector = ImageVector.vectorResource(R.drawable.ic_dark_logo),
            welcomeBackgroundVector = ImageVector.vectorResource(R.drawable.bg_dark_welcome),
            loginBackgroundVector = ImageVector.vectorResource(R.drawable.bg_dark_login),
        )
    } else {
        MySootheDrawables(
            isLight = true,
            logoImageVector = ImageVector.vectorResource(R.drawable.ic_light_logo),
            welcomeBackgroundVector = ImageVector.vectorResource(R.drawable.bg_light_welcome),
            loginBackgroundVector = ImageVector.vectorResource(R.drawable.bg_light_login),
        )
    }

    CompositionLocalProvider(
        LocalMySootheDrawables provides themeDrawables
    ) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object MyTheme {
    val drawables: MySootheDrawables
        @Composable
        @ReadOnlyComposable
        get() = LocalMySootheDrawables.current
}
