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

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
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
    @DrawableRes val logoImageRes: Int,
    @DrawableRes val welcomeBackgroundRes: Int,
    @DrawableRes val loginBackgroundRes: Int,
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
    }.let { staticColors ->
        staticColors.copy(
            primary = animateColorAsState(staticColors.primary).value,
            secondary = animateColorAsState(staticColors.secondary).value,
            background = animateColorAsState(staticColors.background).value,
            surface = animateColorAsState(staticColors.surface).value,
            onPrimary = animateColorAsState(staticColors.onPrimary).value,
            onSecondary = animateColorAsState(staticColors.onSecondary).value,
            onBackground = animateColorAsState(staticColors.onBackground).value,
            onSurface = animateColorAsState(staticColors.onSurface).value,
        )
    }

    val themeDrawables = if (darkTheme) {
        MySootheDrawables(
            isLight = false,
            logoImageRes = R.drawable.ic_dark_logo,
            welcomeBackgroundRes = R.drawable.bg_dark_welcome,
            loginBackgroundRes = R.drawable.bg_dark_login,
        )
    } else {
        MySootheDrawables(
            isLight = true,
            logoImageRes = R.drawable.ic_light_logo,
            welcomeBackgroundRes = R.drawable.bg_light_welcome,
            loginBackgroundRes = R.drawable.bg_light_login,
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
