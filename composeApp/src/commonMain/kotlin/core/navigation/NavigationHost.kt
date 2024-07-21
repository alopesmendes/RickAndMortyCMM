package core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import features.characters.presentation.screens.CharactersScreen

@Composable
fun NavigationHost(modifier: Modifier, navHostController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Routes.Characters.fullRoute(),
    ) {
        composable(Routes.Characters.fullRoute(), arguments = Routes.Characters.navParams()) {
            CharactersScreen(
                navHostController = navHostController,
            )
        }

        composable(Routes.CharacterDetail.fullRoute(), arguments = Routes.CharacterDetail.navParams()) {

        }
    }
}