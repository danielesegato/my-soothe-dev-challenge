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

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import dev.danielesegato.androiddevchallenge.mysoothe.R
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.MyTheme
import dev.danielesegato.androiddevchallenge.mysoothe.ui.theme.bottomNavigationElevation

val horizontalPadding = 16.dp

enum class Tab {
    Home,
    Profile,
}

data class TabItem<T>(
    val id: T,
    @StringRes val text: Int,
    val icon: ImageVector,
)

val homeSections = listOf(
    TabItem(
        Tab.Home,
        R.string.home_tab_home,
        Icons.Default.Spa,
    ),
    TabItem(
        Tab.Profile,
        R.string.home_tab_profile,
        Icons.Default.AccountCircle,
    )
)

@Composable
fun Home() {
    ProvideWindowInsets {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            HomeContent()
        }
    }
}

@Composable
private fun HomeContent() {
    Scaffold(
        modifier = Modifier,
        bottomBar = {
            HomeNavBar(
                homeSections,
                currentTab = Tab.Home,
                onTabClicked = { tab, reSelection ->
                }
            )
        },
        floatingActionButton = {
            HomeFab()
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) { scaffoldPadding ->
        HomeTabContent(
            modifier = Modifier
                .padding(scaffoldPadding)
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
private fun HomeTabContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        Search()
        HomeSubSection(title = stringResource(R.string.home_section_favcollection)) {
            TwoRowCarousel(
                records = favoriteCollection,
                contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 0.dp),
            ) { record ->
                RectangularItem(
                    record,
                    onItemClick = {},
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HomeSubSection(title = stringResource(R.string.home_section_alignbody)) {
            SingleRowCarousel(
                records = alignYourBody,
                contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 0.dp),
            ) { record ->
                CircleItem(
                    record,
                    onItemClick = {},
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HomeSubSection(title = stringResource(R.string.home_section_alignmind)) {
            SingleRowCarousel(
                records = alignYourMind,
                contentPadding = PaddingValues(horizontal = horizontalPadding, vertical = 0.dp)
            ) { record ->
                CircleItem(
                    record,
                    onItemClick = {},
                )
            }
        }
        Spacer(modifier = Modifier.height(56.dp))
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
            imageVector = Icons.Default.PlayArrow,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = stringResource(R.string.home_action_play),
        )
    }
}

@Composable
private fun <T> HomeNavBar(
    tabs: List<TabItem<T>>,
    currentTab: T = tabs[0].id,
    onTabClicked: (sectionId: T, reSelection: Boolean) -> Unit = { _, _ -> },
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
        tabs.forEach { section ->
            BottomNavigationItem(
                selected = section.id == currentTab,
                onClick = { onTabClicked(section.id, section.id == currentTab) },
                icon = {
                    Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = section.icon,
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
private fun Search(
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
        ),
        leadingIcon = {
            Image(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Default.Search,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                contentDescription = stringResource(R.string.home_field_search_leadingicon_desc),
            )
        },
        label = {
            Text(
                text = stringResource(R.string.home_field_search_label),
                style = MaterialTheme.typography.body1,
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.home_field_search_hint),
                style = MaterialTheme.typography.body1,
            )
        },
    )
}

@Composable
private fun HomeSubSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = horizontalPadding),
            text = title,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
        )
        content()
    }
}

@Preview
@Composable
private fun PreviewHomeLight() {
    MyTheme(darkTheme = false) {
        Home()
    }
}

@Preview
@Composable
private fun PreviewHomeDark() {
    MyTheme(darkTheme = true) {
        Home()
    }
}
