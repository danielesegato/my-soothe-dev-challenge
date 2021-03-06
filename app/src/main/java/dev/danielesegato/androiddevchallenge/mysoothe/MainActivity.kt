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
package dev.danielesegato.androiddevchallenge.mysoothe

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import dev.danielesegato.androiddevchallenge.mysoothe.home.Home
import dev.danielesegato.androiddevchallenge.mysoothe.login.Login
import dev.danielesegato.androiddevchallenge.mysoothe.login.Welcome
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

object Destinations {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val HOME = "home"
}

// Start building your app here!
@ExperimentalComposeUiApi
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.WELCOME) {
        // animations between destination is currently (1.0.0-alpha09) not supported, see
        // https://issuetracker.google.com/issues/172112072
        // and I couldn't find an easy / straight forward way to animate between screens
        // except the one suggested in that issue, which i s a bit hacky

        composable(Destinations.WELCOME) {
            Welcome(
                onLogin = { navController.navigate(Destinations.LOGIN) }
            )
        }
        composable(Destinations.LOGIN) {
            Login(
                onLoginSuccessful = { navController.navigate(Destinations.HOME) }
            )
        }
        composable(Destinations.HOME) {
            Home()
        }
    }
}

@ExperimentalComposeUiApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@ExperimentalComposeUiApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
