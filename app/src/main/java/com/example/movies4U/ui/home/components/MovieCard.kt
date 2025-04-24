package com.example.movies4U.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies4U.ui.home.itemSpacing

//see any line missing or not


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = MaterialTheme.shapes.large,
    bgColor: Color = Color.Black.copy(alpha = .8f),
    content: @Composable () -> Unit,
) {
    Card(
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = bgColor,
            contentColor = Color.White
        ),
        modifier = modifier
    ) {
        content()
    }
}
@Preview(showBackground = true)
@Composable
private fun PreviewMovieCard() {
    MovieCard {
        Text(text="Action",modifier = Modifier.padding(itemSpacing))
    }
}



