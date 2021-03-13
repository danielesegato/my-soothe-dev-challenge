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
package dev.danielesegato.androiddevchallenge.mysoothe.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme

@Composable
fun Login(
    onLoginSuccessful: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            imageVector = MyTheme.drawables.loginBackgroundVector,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 32.dp),
                style = MaterialTheme.typography.h1,
                text = "LOG IN",
            )
            MySootheButton(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = "LOG IN",
                onClick = { /*TODO*/ },
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginLight() {
    MyTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background) {
            Login()
        }
    }
}

@Preview
@Composable
fun PreviewLoginDark() {
    MyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            Login()
        }
    }
}
