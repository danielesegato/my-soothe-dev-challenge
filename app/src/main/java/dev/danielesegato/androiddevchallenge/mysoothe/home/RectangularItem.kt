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
package dev.danielesegato.androiddevchallenge.mysoothe.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme
import kotlin.math.roundToInt

@Composable
fun RectangularItem(
    record: Record,
    onItemClick: (record: Record) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .clickable { onItemClick(record) },
        color = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier
                .size(width = 192.dp, height = 56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CoilImage(
                modifier = Modifier.size(56.dp),
                data = record.squareImageUrlOfSize((56.dp).value.roundToInt()),
                contentDescription = null,
                loading = {
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.secondary),
                    )
                },
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .wrapContentSize(align = Alignment.CenterStart),
                text = record.title,
                style = MaterialTheme.typography.h3,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLight() {
    MyTheme(darkTheme = false) {
        PreviewContent()
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MyTheme(darkTheme = true) {
        PreviewContent()
    }
}

@Composable
private fun PreviewContent() {
    RectangularItem(record = favoriteCollection.first())
}
