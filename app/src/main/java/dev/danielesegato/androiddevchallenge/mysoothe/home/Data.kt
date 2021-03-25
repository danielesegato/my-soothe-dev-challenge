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

import dev.danielesegato.androiddevchallenge.mysoothe.common.renderSquareImageUrl

data class Record(
    val title: String,
    val imageUrl: String,
) {
    fun squareImageUrlOfSize(sizePx: Int) = imageUrl.renderSquareImageUrl(sizePx)
}

val favoriteCollection = listOf(
    Record(
        "Short mantras",
        // this does not exist anymore, I took one somewhat similar
        // "https://images.pexels.com/photos/1825206/body-of-water-view-1825206.jpeg"
        "https://images.pexels.com/photos/734973/pexels-photo-734973.jpeg"
    ),
    Record(
        "Nature meditations",
        "https://images.pexels.com/photos/3571551/pexels-photo-3571551.jpeg"
    ),
    Record(
        "Stress and anxiety",
        "https://images.pexels.com/photos/1557238/pexels-photo-1557238.jpeg"
    ),
    Record(
        "Self-massage",
        "https://images.pexels.com/photos/1029604/pexels-photo-1029604.jpeg"
    ),
    Record(
        "Overwhelmed",
        "https://images.pexels.com/photos/3560044/pexels-photo-3560044.jpeg"
    ),
    Record(
        "Nightly wind down",
        "https://images.pexels.com/photos/924824/pexels-photo-924824.jpeg"
    ),
)
val alignYourBody = listOf(
    Record(
        "Inversions",
        "https://images.pexels.com/photos/317157/pexels-photo-317157.jpeg"
    ),
    Record(
        "Quick yoga",
        "https://images.pexels.com/photos/1812964/pexels-photo-1812964.jpeg"
    ),
    Record(
        "Streching",
        "https://images.pexels.com/photos/4056723/pexels-photo-4056723.jpeg"
    ),
    Record(
        "Tabata",
        "https://images.pexels.com/photos/4662438/pexels-photo-4662438.jpeg"
    ),
    Record(
        "HIIT",
        "https://images.pexels.com/photos/999309/pexels-photo-999309.jpeg"
    ),
    Record(
        "Pre-natal yoga",
        "https://images.pexels.com/photos/396133/pexels-photo-396133.jpeg"
    )
)
val alignYourMind = listOf(
    Record(
        "Meditate",
        "https://images.pexels.com/photos/3822622/pexels-photo-3822622.jpeg"
    ),
    Record(
        "With Kids",
        "https://images.pexels.com/photos/3094230/pexels-photo-3094230.jpeg"
    ),
    Record(
        "Aromatherapy",
        "https://images.pexels.com/photos/4498318/pexels-photo-4498318.jpeg"
    ),
    Record(
        "On the go",
        "https://images.pexels.com/photos/1241348/pexels-photo-1241348.jpeg"
    ),
    Record(
        "With pets",
        "https://images.pexels.com/photos/4056535/pexels-photo-4056535.jpeg"
    ),
    Record(
        "High stress",
        "https://images.pexels.com/photos/897817/pexels-photo-897817.jpeg"
    )
)
