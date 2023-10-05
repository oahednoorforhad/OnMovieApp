package com.example.onmovie

import HomeScreen
import MovieScreen
import SettingsScreen
import WatchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Nav(navController: NavHostController) {


    NavHost(navController = navController, startDestination = "HomeScreen"){

        composable(route = "HomeScreen"){
            HomeScreen(navController)
        }
        composable(route = "WatchScreen"){
            WatchScreen(navController)
        }
        composable(route = "SettingsScreen"){
            SettingsScreen(navController)
        }
        composable(route = "MovieScreen/{movie}",
            arguments = listOf(
                navArgument(name = "movie"){
                    type = NavType.StringType
                }
            )
        ){
            backStackEntry ->
            MovieScreen(navController,
                backStackEntry.arguments?.getString("movie"))
        }
    }
}