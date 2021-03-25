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

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.danielesegato.androiddevchallenge.mysoothe.R
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme

@ExperimentalComposeUiApi
@Composable
fun Login(
    onLoginSuccessful: () -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = MyTheme.drawables.loginBackgroundRes),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        LoginContent(onLoginSuccessful)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun LoginContent(
    onLoginSuccessful: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var showClearPassword by remember { mutableStateOf(false) }

    val passwordHideTransformer = remember { PasswordSeeLastCharVisualTransformation() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
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
                .height(56.dp),
            value = username,
            onValueChange = { username = it },
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
            ),
            // TODO how do I increase the indicator width?
            placeholder = {
                // TODO how do I add padding to it?
                Text(
                    text = stringResource(R.string.login_field_email_hint),
                    style = MaterialTheme.typography.body1,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    // TODO this do not works
                    defaultKeyboardAction(ImeAction.Next)
                }
            ),
        )
        Spacer(Modifier.height(8.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.login_field_password_hint),
                    style = MaterialTheme.typography.body1,
                )
            },
            trailingIcon = {
                IconButton(
                    // this offset is because TextField has a default padding of 12dp
                    // that cannot be overridden
                    modifier = Modifier.offset(x = 12.dp),
                    onClick = { showClearPassword = !showClearPassword },
                ) {
                    Crossfade(targetState = showClearPassword) { isClear ->
                        if (isClear) {
                            Icon(
                                imageVector = Icons.Default.VisibilityOff,
                                contentDescription = stringResource(R.string.home_field_password_hide)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Visibility,
                                contentDescription = stringResource(R.string.home_field_password_show)
                            )
                        }
                    }
                }
            },
            visualTransformation = if (showClearPassword) {
                VisualTransformation.None
            } else {
                passwordHideTransformer
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    defaultKeyboardAction(ImeAction.Next)
                    keyboardController?.hideSoftwareKeyboard()
                }
            ),
        )
        Spacer(modifier = Modifier.height(8.dp))
        MySootheButton(
            text = stringResource(R.string.login_btn_login),
            onClick = { onLoginSuccessful() },
        )
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 32.dp),
            text = buildAnnotatedString {
                append(stringResource(R.string.login_label_noaccount_part1_question))
                append(' ')
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
        )
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
private fun PreviewLoginLight() {
    MyTheme(darkTheme = false) {
        Login()
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
private fun PreviewLoginDark() {
    MyTheme(darkTheme = true) {
        Login()
    }
}
