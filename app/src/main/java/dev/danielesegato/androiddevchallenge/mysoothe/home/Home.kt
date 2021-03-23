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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.insets.LocalWindowInsets
import dev.danielesegato.androiddevchallenge.mysoothe.R
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.bottomNavigationElevation
import kotlin.math.roundToInt

val horizontalPadding = 16.dp

data class NavSection(
    val id: String,
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
)

val homeSections = listOf(
    NavSection(
        "HOME",
        R.string.home_tab_home,
        R.drawable.ic_baseline_spa_24,
    ),
    NavSection(
        "PROFILE",
        R.string.home_tab_profile,
        R.drawable.ic_baseline_account_circle_24,
    )
)

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            HomeNavBar(homeSections)
        },
        floatingActionButton = {
            HomeFab()
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) { scaffoldPadding ->
        Home(
            modifier = Modifier
                .padding(scaffoldPadding)
        )
    }
}

@Composable
private fun Home(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Search(
            Modifier.padding(top = 56.dp)
        )
        HomeSubSection(title = stringResource(R.string.home_section_favcollection)) {
            TwoRowCarousel(favoriteCollection) { record ->
                RectangularItem(
                    record,
                    onItemClick = {},
                )
            }
        }
        HomeSubSection(title = stringResource(R.string.home_section_alignbody)) {
            SingleRowCarousel(alignYourBody) { record ->
                CircleItem(
                    record,
                    onItemClick = {},
                )
            }
        }
        HomeSubSection(title = stringResource(R.string.home_section_alignmind)) {
            SingleRowCarousel(alignYourMind) { record ->
                CircleItem(
                    record,
                    onItemClick = {},
                )
            }
        }
    }
}

@Composable
private fun HomeFab(
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_play_arrow_24),
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(R.string.home_action_play),
        )
    }
}

@Composable
fun HomeNavBar(
    sections: List<NavSection>,
    currentSectionId: String = sections[0].id,
    onSectionClicked: (sectionId: String, reSelection: Boolean) -> Unit = { _, _ -> },
) {
    val insets = LocalWindowInsets.current
    val navBarInsetDp = with(LocalDensity.current) {
        insets.navigationBars.bottom.toDp()
    }
    BottomNavigation(
        modifier = Modifier
            .padding(bottom = navBarInsetDp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = bottomNavigationElevation,
    ) {
        sections.forEach { section ->
            BottomNavigationItem(
                selected = section.id == currentSectionId,
                onClick = { onSectionClicked(section.id, section.id == currentSectionId) },
                icon = {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = ImageVector.vectorResource(id = section.icon),
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = section.text),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground,
                    )
                },
            )
        }
    }
}

@Composable
fun Search(
    modifier: Modifier = Modifier,
    onSearchChanged: (term: String) -> Unit = {},
) {
    var searchTerm by remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = horizontalPadding),
        value = searchTerm,
        onValueChange = {
            searchTerm = it
            val q = it.text.trim()
            onSearchChanged(q)
        },
        singleLine = true,
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            textColor = MaterialTheme.colors.onSurface,
        ),
        leadingIcon = {
            Image(
                modifier = Modifier.size(18.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_24),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = stringResource(R.string.home_field_search_leadingicon_desc),
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.home_field_search_hint),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
            )
        },
    )
}

@Composable
fun HomeSubSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp)
                .padding(horizontal = horizontalPadding),
            text = title,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
        )
        content()
    }
}

@Composable
fun SingleRowCarousel(
    records: List<Record>,
    itemContent: @Composable LazyItemScope.(record: Record) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 8.dp),
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
    itemContent: @Composable LazyItemScope.(record: Record) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(records.chunked(2)) { recordPair ->
            Column(
                verticalArrangement = Arrangement.Top,
            ) {
                itemContent(recordPair[0])
                if (recordPair.size > 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                    itemContent(recordPair[1])
                }
            }
        }
    }
}

@Composable
fun RectangularItem(
    record: Record,
    onItemClick: (record: Record) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .size(width = 192.dp, height = 56.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colors.surface)
            .clickable { onItemClick(record) },
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
            color = MaterialTheme.colors.onSurface,
        )
    }
}

@Composable
fun CircleItem(
    record: Record,
    onItemClick: (record: Record) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable { onItemClick(record) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CoilImage(
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
            data = record.squareImageUrlOfSize((88.dp).value.roundToInt()),
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
                .fillMaxWidth()
                .paddingFromBaseline(24.dp)
                .wrapContentSize(align = Alignment.Center),
            text = record.title,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
        )
    }
}

@Preview
@Composable
fun PreviewHomeLight() {
    MyTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun PreviewHomeDark() {
    MyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen()
        }
    }
}
