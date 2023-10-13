import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.onmovie.model.getMovies

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    navController: NavController,
    movieid: String?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies",
                    color = Color.White) },
                modifier = Modifier
                    .offset(0.dp, 5.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = Color.Transparent),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton( onClick = { navController.popBackStack() }){
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = Color.White
    ) {
        val movie = getMovies().filter { movie ->
            movie.id == movieid
        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
                ,
//.background(brush = Brush.verticalGradient(colors = listOf(Color.White, Color.Black)))
//.alpha(0.1F),
            color = MaterialTheme.colorScheme.inverseSurface
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.1F)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = movie[0].poster),
                    contentDescription = "Poster",
                    contentScale = ContentScale.FillHeight
                )
            }
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .offset(0.dp, 65.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(Color.Transparent),
                //elevation = CardDefaults.cardElevation(defaultElevation = 3.dp, pressedElevation = 10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(15.dp)
                            .size(110.dp, 200.dp),
                        shape = RoundedCornerShape(10),
                        shadowElevation = 3.dp
                    ) {

                        Image(
                            painter = rememberAsyncImagePainter(model = movie[0].poster),
                            contentDescription = "Movie Poster",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column {
                        Text(
                            text = movie[0].title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = "Director: ${movie[0].director}",
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Genre: ${movie[0].genre}",
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Year: ${movie[0].year}",
                            fontSize = 13.sp
                        )
                        Text(
                            text = "Rating: ${movie[0].rating}",
                            fontSize = 13.sp
                        )
                        Divider(
                            modifier = Modifier.offset(0.dp, 50.dp),
                            thickness = 1.dp,
                            color = Color.Black
                        )
                    }
                }
            }
            Text(
                text = "Plot: ${movie[0].plot}",
                modifier = Modifier
                    .offset(0.dp, 290.dp)
                    .padding(20.dp)
            )
            LazyRow {
                items(movie[0].images) { image ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(350.dp, 185.dp)
                            .offset(0.dp, 420.dp),
                        colors = CardDefaults.cardColors(Color.Black),
                        elevation = CardDefaults.cardElevation(3.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = image,
                                contentScale = ContentScale.Crop
                            ),
                            contentDescription = "Movie Poster",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

fun IconButton(onClick: () -> Unit) {

}







