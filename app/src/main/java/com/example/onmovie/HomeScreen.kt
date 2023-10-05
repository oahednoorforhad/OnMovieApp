import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material.icons.rounded.SmartDisplay
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.Companion.DefaultTransform
import coil.compose.rememberAsyncImagePainter
import com.example.onmovie.BottomNavigationItem
import com.example.onmovie.R
import com.example.onmovie.model.Movie
import com.example.onmovie.model.getMovies

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController)
{
    val movieList = getMovies()
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.inverseSurface
    ) {
            Column() {
                LazyColumn{
                    items(items = movieList){
                        MovieRow(movie= it){ movie ->
                            navController.navigate("MovieScreen/${movie}")
                        }
                    }
                }
            }
    }
}

@Composable
fun MovieRow(movie: Movie,
             onItemClick: (String)-> Unit = {} ){
    Card(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(200.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp, pressedElevation = 10.dp)
    ) {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.inverseSurface) {
            Image(
                painter = rememberAsyncImagePainter(model = movie.images[0],
                    filterQuality = FilterQuality.Low),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.FillWidth,
                alpha = 0.1F
            )
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start) {
                Surface(modifier = Modifier
                    .padding(15.dp)
                    .size(110.dp, 200.dp),
                    shape = RoundedCornerShape(10),
                    shadowElevation = 3.dp) {
//                Icon(
//                    imageVector = Icons.Default.Movie,
//                    contentDescription = "Movie"
//                )
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.poster),
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.Crop
                    )
                }
                Column {
                    Text(text = movie.title,
                        fontWeight = Bold,
                        fontSize = 17.sp
                    )
                    Text(text = "Director: ${movie.director}",
                        fontSize = 13.sp)
                    Text(text = "Genre: ${movie.genre}",
                        fontSize = 13.sp)
                    Text(text = "Year: ${movie.year}",
                        fontSize = 13.sp)
                    Text(text = "Rating: ${movie.rating}",
                        fontSize = 13.sp)
                }
        }
        }
    }

}

