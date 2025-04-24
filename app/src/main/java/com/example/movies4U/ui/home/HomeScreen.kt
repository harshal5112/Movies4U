package com.example.movies4U.ui.home

import android.R.attr.maxHeight
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.movies4U.ui.home.components.BodyContent
import com.example.movies4U.ui.home.components.TopContent
import kotlinx.coroutines.delay

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieClick: (id: Int) -> Unit,
) {
    var isAutoScrolling by remember { mutableStateOf(true) }
    val state by homeViewModel.homeState.collectAsStateWithLifecycle() //  unwrapped state.value
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { state.discoverMovies.size } //  simplified access
    )
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (isDragged) {
            isAutoScrolling = false
        } else {
            isAutoScrolling = true
            delay(4000)
            with(pagerState) {
                val target =
                    if (currentPage < state.discoverMovies.size - 1) currentPage + 1 else 0
                scrollToPage(target)
            }
        }
    }
    Box(modifier = modifier) {
        AnimatedVisibility(visible = state.error != null) {
            Text(
                text = state.error ?: "Unknown Error",
                color = MaterialTheme.colorScheme.error,
                maxLines = 2
            )
        }
        AnimatedVisibility(visible = !state.isLoading && state.error == null) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val boxHeight = this.maxHeight            //added this no in code
                val topItemHeight = boxHeight * .45f
                val bodyItemHeight = boxHeight * .55f
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(defaultPadding),
                    pageSize = PageSize.Fill,
                    pageSpacing = itemSpacing
                ) { page ->
                    if (isAutoScrolling) {
                        AnimatedContent(targetState = page, label = " ") { index ->
                            TopContent(
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .heightIn(min = topItemHeight),
                                movie = state.discoverMovies[index],
                                onMovieClick = {
                                    onMovieClick(it)
                                }
                            )
                        }
                    } else {
                        TopContent(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .heightIn(min = topItemHeight),
                            movie = state.discoverMovies[page],
                            onMovieClick = {
                                onMovieClick(it)
                            }
                        )
                    }
                }
                BodyContent(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .heightIn(max = bodyItemHeight),
                    discoverMovies = state.discoverMovies,
                    trendingMovies = state.trendingMovies,
                    onMovieClick = onMovieClick
                )
            }
        }
    }
}
