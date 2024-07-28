package core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import core.entities.ScaffoldItemsState
import features.characterDetail.presentation.screens.CharacterDetailScreen
import features.characters.presentation.screens.CharactersScreen
import features.episodeDetail.presentation.screens.EpisodeDetailScreen
import features.episodes.presentation.screens.EpisodesScreen
import features.locationDetail.presentation.screens.LocationDetailScreen
import features.locations.presentation.screens.LocationsScreen
import org.koin.core.parameter.parametersOf


@Composable
fun NavigationHost(
    modifier: Modifier,
    navHostController: NavHostController,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit = {},
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Routes.Characters.fullRoute(),
    ) {
        composable(Routes.Characters.fullRoute(), arguments = Routes.Characters.navParams()) {
            CharactersScreen(
                navHostController = navHostController,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }

        composable(Routes.CharacterDetail.fullRoute(), arguments = Routes.CharacterDetail.navParams()) {
            CharacterDetailScreen(
                parametersHolder = parametersOf(SavedStateHandle.createHandle(null, it.arguments)),
                navHostController = navHostController,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }

        composable(Routes.Episodes.fullRoute(), arguments = Routes.Episodes.navParams()) {
            EpisodesScreen(
                navHostController = navHostController,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }

        composable(Routes.EpisodeDetail.fullRoute(), arguments = Routes.EpisodeDetail.navParams()) {
            EpisodeDetailScreen(
                parametersHolder = parametersOf(SavedStateHandle.createHandle(null, it.arguments)),
                navHostController = navHostController,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }

        composable(Routes.Locations.fullRoute(), arguments = Routes.Locations.navParams()) {
            LocationsScreen(
                navHostController = navHostController,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }

        composable(Routes.LocationDetail.fullRoute(), arguments = Routes.LocationDetail.navParams()) {
            LocationDetailScreen(
                parametersHolder = parametersOf(SavedStateHandle.createHandle(null, it.arguments)),
                navHostController = navHostController,
                onScaffoldItemsState = onScaffoldItemsState,
            )
        }
    }
}