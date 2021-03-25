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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme

@Composable
fun SingleRowCarousel(
    records: List<Record>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemContent: @Composable LazyItemScope.(record: Record) -> Unit,
) {
    LazyRow(
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(records) { record ->
            itemContent(record)
        }
    }
}

@Composable
fun TwoRowCarousel(
    records: List<Record>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    itemContent: @Composable LazyItemScope.(record: Record) -> Unit
) {
    val chunkedRecords = remember {
        records.chunked(2)
            .map { chunk ->
                if (chunk.size > 1) {
                    chunk[0] to chunk[1]
                } else {
                    chunk[0] to null
                }
            }
    }
    LazyRow(
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(chunkedRecords) { recordPair ->
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                val (top, bottom) = recordPair
                itemContent(top)
                if (bottom != null) {
                    itemContent(bottom)
                }
            }
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
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = MaterialTheme.colors.background,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TwoRowCarousel(
                records = favoriteCollection,
                PaddingValues(16.dp),
            ) {
                RectangularItem(record = it)
            }
            SingleRowCarousel(
                records = alignYourMind,
                PaddingValues(16.dp),
            ) {
                CircleItem(record = it)
            }
        }
    }
}
