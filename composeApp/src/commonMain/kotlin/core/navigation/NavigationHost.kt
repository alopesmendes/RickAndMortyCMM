package core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import features.characterDetail.presentation.screens.CharacterDetailScreen
import features.characters.presentation.screens.CharactersScreen
import features.episodeDetail.presentation.screens.EpisodeDetailScreen
import features.episodes.presentation.screens.EpisodesScreen
import features.locationDetail.presentation.screens.LocationDetailScreen
import features.locations.presentation.screens.LocationsScreen
import org.koin.core.parameter.parametersOf

@Composable
fun ObserveNavigation(navController: NavHostController, onDestinationChanged: (String) -> Unit) {
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            destination.route?.let { onDestinationChanged(it) }
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}


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
            CharacterDetailScreen(
                parametersHolder = parametersOf(SavedStateHandle.createHandle(null, it.arguments)),
                navHostController = navHostController
            )
        }

        composable(Routes.Episodes.fullRoute(), arguments = Routes.Episodes.navParams()) {
            EpisodesScreen(
                navHostController = navHostController,
            )
        }

        composable(Routes.EpisodeDetail.fullRoute(), arguments = Routes.EpisodeDetail.navParams()) {
            EpisodeDetailScreen(
                parametersHolder = parametersOf(SavedStateHandle.createHandle(null, it.arguments)),
                navHostController = navHostController,
            )
        }

        composable(Routes.Locations.fullRoute(), arguments = Routes.Locations.navParams()) {
            LocationsScreen(
                navHostController = navHostController,
            )
        }

        composable(Routes.LocationDetail.fullRoute(), arguments = Routes.LocationDetail.navParams()) {
            LocationDetailScreen(
                navHostController = navHostController,
            )
        }
    }
}