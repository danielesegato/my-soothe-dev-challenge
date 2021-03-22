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
package dev.danielesegato.androiddevchallenge.mysoothe.common

// looks like https://images.pexels.com use imgix.com under the hood and so we have those features
// https://docs.imgix.com/apis/rendering

enum class ImageRenderFormat {
    srgb,
    adobergb1998,
    tinysrgb,
    strip,
}

fun String.renderSquareImageUrl(
    sizePx: Int,
    compress: Boolean = true,
    format: ImageRenderFormat = ImageRenderFormat.tinysrgb,
): String {
    if (this.indexOf('?') != -1) return this
    return StringBuilder(this).apply {
        var prependChar = '?'
        fun appendSeparator() {
            append(prependChar)
            if (prependChar == '?') prependChar = '&'
        }
        if (compress) {
            appendSeparator()
            append("auto=compress")
        }
        appendSeparator()
        append("cs=")
        append(format.name)
        appendSeparator()
        append("w=")
        append(sizePx)
        appendSeparator()
        append("h=")
        append(sizePx)
        appendSeparator()
        append("fit=crop")
    }.toString()
}
