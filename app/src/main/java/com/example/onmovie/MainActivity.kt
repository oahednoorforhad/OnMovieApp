package com.example.onmovie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material.icons.rounded.SmartDisplay
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.onmovie.ui.theme.OnMovieTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Myapp {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.inverseSurface
                ) {
                    MainContent()
                }
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
    val route: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Myapp(content: @Composable () -> Unit) {
    OnMovieTheme {
        MainContent()
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Movie,
            unselectedIcon = Icons.Outlined.Movie,
            hasNews = false,
            badgeCount = null,
            route = "HomeScreen"
        ),
        BottomNavigationItem(
            title = "Watch",
            selectedIcon = Icons.Rounded.SmartDisplay,
            unselectedIcon = Icons.Outlined.SmartDisplay,
            hasNews = false,
            badgeCount = 33,
            route = "WatchScreen"

        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
            badgeCount = null,
            route = "SettingsScreen"
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                modifier = Modifier
                    .offset(0.dp, 5.dp)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(6.dp)),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    IconButton(onClick = {
                        when (currentRoute) {
                            "HomeScreen", "WatchScreen", "SettingsScreen" -> {
                               //TODO//
                            }
                            else -> {
                                navController.popBackStack()
                            }
                        }
                    }) {
                        when (currentRoute) {
                            "HomeScreen", "WatchScreen", "SettingsScreen" -> {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }

                            else -> {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route)
                        },
                        label = { Text(text = item.title) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                })
                            {
                                Icon(
                                    imageVector = if (index == selectedItemIndex)
                                        item.selectedIcon
                                    else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }
    )
    {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Nav(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Myapp {
        MainContent()
    }
}




