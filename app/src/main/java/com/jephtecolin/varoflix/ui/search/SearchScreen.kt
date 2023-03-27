package com.jephtecolin.varoflix.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jephtecolin.varoflix.ui.components.MovieCard

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onBackPress: () -> Unit
) {
    val movies by viewModel.movies.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column {
        SearchView(state = textState,
            onSearchClick = { viewModel.getMoviesByKeywords(textState.value.text)},
        onBackPress = onBackPress)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 24.dp),
            content = {
                items(movies) { movie ->
                    MovieCard(movie = movie, onClickCard = {})
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(state: MutableState<TextFieldValue>,
               onSearchClick: () -> Unit,
                onBackPress: () -> Unit) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        trailingIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .background(color = Color.White)
                )
            }

        },
        leadingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(48.dp)
                    )
                }
            } else {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(48.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}