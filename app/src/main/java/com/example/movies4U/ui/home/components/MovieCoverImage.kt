package com.example.movies4U.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movies4U.movie.domain.models.Movie
import com.example.movies4U.ui.home.itemSpacing
import com.example.movies4U.utils.K

@Composable
fun MovieCoverImage(
    modifier: Modifier = Modifier,
    movie: Movie,
    onMovieClick: (id: Int) -> Unit,
) {
    val imageRequest =
        ImageRequest
            .Builder(LocalContext.current)
            .data("${K.BASE_IMAGE_URL}${movie.posterPath}")
            .crossfade(true).build()
    Box(
        modifier = modifier
            .size(width = 150.dp, height = 250.dp)
            .padding(itemSpacing)
            .clickable { onMovieClick(movie.id) }
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.shapes.medium)
                .shadow(elevation = 4.dp),
            contentScale = ContentScale.Crop
        )
        MovieCard(
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Bookmark,
                contentDescription = "Bookmark",
                modifier = Modifier.padding(4.dp)
            )
        }
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary.copy(alpha = .8f),
            contentColor = Color.White,
            shape = RoundedCornerShape(
                bottomStart = 30.dp,
                bottomEnd = 30.dp
            )
        ) {
            Box(contentAlignment = Alignment.Center,modifier= Modifier.fillMaxWidth()) {
                Text(text=movie.title,maxLines=1)
            }
        }
    }
}