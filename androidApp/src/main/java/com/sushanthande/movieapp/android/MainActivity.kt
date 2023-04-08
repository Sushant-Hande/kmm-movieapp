package com.sushanthande.movieapp.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.sushanthande.movieapp.MovieSDK
import com.sushanthande.movieapp.network.model.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val movieSDK: MovieSDK = MovieSDK()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScope.launch {
            kotlin.runCatching {
                movieSDK.getPopularMovies()
            }.onSuccess { movieResponse ->
                setContent {
                    MyApplicationTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            Scaffold(
                                topBar = {
                                    TopAppBar(backgroundColor = Color.Black) {
                                        Row {
                                            Text(
                                                text = "Movie App",
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(start = 10.dp)
                                            )
                                        }
                                    }
                                },
                                content = {
                                    MovieGrid(
                                        movieList = movieResponse.results,
                                        modifier = Modifier.padding(it)
                                    )
                                }
                            )
                        }
                    }
                }
            }.onFailure {
                Toast.makeText(this@MainActivity, it.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }
    }

}

@Composable
fun MovieGrid(
    movieList: List<Result>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(modifier = modifier.padding(0.dp),
        columns = GridCells.Fixed(2),
        content = {
            items(movieList) { movie ->
                Box(
                    modifier = modifier.clip(RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        imageModel = movie.getImagePath(),
                        contentScale = ContentScale.Fit,
                        modifier = modifier
                            .width(150.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(5.dp)
                    )
                }
            }
        })
}