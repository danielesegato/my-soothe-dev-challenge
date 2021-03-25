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

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * The Visual Filter can be used for password Input Field. The last character is always visible.
 *
 * Note that this visual filter only works for ASCII characters.
 *
 * @param mask The mask character used instead of original text.
 */
class PasswordSeeLastCharVisualTransformation(val mask: Char = '\u2022') : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString.Builder()
                .apply {
                    when (val l = text.text.length) {
                        0 -> {
                        }
                        1 -> {
                            append(text.text)
                        }
                        else -> {
                            repeat(l - 1) {
                                append(mask)
                            }
                            append(
                                AnnotatedString(
                                    text = text.text.substring(l - 1),
                                    spanStyle = SpanStyle()
                                )
                            )
                        }
                    }
                }
                .toAnnotatedString(),
            OffsetMapping.Identity
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PasswordSeeLastCharVisualTransformation) return false
        if (mask != other.mask) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}
