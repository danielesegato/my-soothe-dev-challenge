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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.danielesegato.androiddevchallenge.mysoothe.R
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme

@Composable
fun Login(
    onLoginSuccessful: () -> Unit = {},
) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
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
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 200.dp, bottom = 32.dp),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
                text = stringResource(R.string.login_title),
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                value = username,
                onValueChange = { username = it },
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    textColor = MaterialTheme.colors.onSurface,
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.login_field_email_hint),
                        style = MaterialTheme.typography.body1,
                    )
                },
            )
            TextField(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                value = password,
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation(),
                textStyle = MaterialTheme.typography.body1,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface,
                    textColor = MaterialTheme.colors.onSurface,
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.login_field_password_hint),
                        style = MaterialTheme.typography.body1,
                    )
                },
            )
            MySootheButton(
                modifier = Modifier
                    .padding(top = 8.dp),
                text = stringResource(R.string.login_btn_login),
                onClick = { onLoginSuccessful() },
            )
            Text(
                modifier = Modifier
                    .paddingFromBaseline(top = 32.dp),
                text = buildAnnotatedString {
                    append(stringResource(R.string.login_label_noaccount_part1_question))
                    append(
                        AnnotatedString(
                            stringResource(R.string.login_label_noaccount_part2_signup),
                            spanStyle = SpanStyle(
                                textDecoration = TextDecoration.Underline,
                            )
                        ),
                    )
                },
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
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
