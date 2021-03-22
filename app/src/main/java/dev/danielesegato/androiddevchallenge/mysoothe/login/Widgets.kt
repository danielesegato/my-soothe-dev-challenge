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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme

@Composable
fun MySootheButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    useSecondaryColor: Boolean = false,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(72.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = if (useSecondaryColor) ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary,
        ) else ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
    ) {
        content()
    }
}

@Composable
fun MySootheButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    useSecondaryColor: Boolean = false,
) {
    MySootheButton(modifier, onClick, useSecondaryColor) {
        Text(text = text, style = MaterialTheme.typography.button)
    }
}

@Preview
@Composable
fun PreviewMySootheButtons() {
    Column(modifier = Modifier.fillMaxSize()) {
        MyTheme(darkTheme = false) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MySootheButton(
                    Modifier.padding(16.dp),
                    text = "SIGN UP",
                    onClick = {}
                )
                MySootheButton(
                    Modifier.padding(16.dp),
                    text = "LOGIN",
                    onClick = {},
                    useSecondaryColor = true
                )
            }
        }

        MyTheme(darkTheme = true) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                MySootheButton(
                    Modifier.padding(16.dp),
                    text = "SIGN UP",
                    onClick = {}
                )
                MySootheButton(
                    Modifier.padding(16.dp),
                    text = "LOGIN",
                    onClick = {},
                    useSecondaryColor = true
                )
            }
        }
    }
}
