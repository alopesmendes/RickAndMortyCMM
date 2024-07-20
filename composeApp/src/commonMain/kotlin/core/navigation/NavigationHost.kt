package core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import features.characters.presentation.screens.CharactersScreen
import features.characters.presentation.viewModels.CharactersViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NavigationHost(modifier: Modifier, navHostController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Routes.Characters.fullRoute(),
    ) {
        composable(Routes.Characters.fullRoute()) {
            val viewModel: CharactersViewModel = koinViewModel()
            val characterListState by viewModel.characterListState.collectAsState()
            CharactersScreen(
                charactersListState = characterListState,
            )
        }
    }
}