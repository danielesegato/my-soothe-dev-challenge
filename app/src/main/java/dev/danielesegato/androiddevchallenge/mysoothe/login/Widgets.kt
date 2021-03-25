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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
            .height(72.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = if (useSecondaryColor) ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
        ) else ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
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
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        MyTheme(darkTheme = false) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.background,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MySootheButton(
                        text = "SIGN UP",
                        onClick = {}
                    )
                    MySootheButton(
                        text = "LOGIN",
                        onClick = {},
                        useSecondaryColor = true
                    )
                }
            }
        }

        MyTheme(darkTheme = true) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colors.background,
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    MySootheButton(
                        text = "SIGN UP",
                        onClick = {}
                    )
                    MySootheButton(
                        text = "LOGIN",
                        onClick = {},
                        useSecondaryColor = true
                    )
                }
            }
        }
    }
}
