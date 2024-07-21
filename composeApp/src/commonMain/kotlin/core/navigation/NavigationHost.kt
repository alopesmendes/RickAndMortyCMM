package core.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import core.util.Tools.rememberFlowWithLifecycle
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.intents.CharacterListIntent
import features.characters.presentation.screens.CharactersScreen
import features.characters.presentation.viewModels.CharacterListViewModel
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
        composable(Routes.Characters.fullRoute(), arguments = Routes.Characters.navParams()) {
            val viewModel: CharacterListViewModel = koinViewModel()
            val characterListState by viewModel.state.collectAsStateWithLifecycle()
            val effect = rememberFlowWithLifecycle(viewModel.effects)

            LaunchedEffect(effect) {
                effect.collect {
                    when (it) {
                        is CharacterListEffect.NavigateToCharacterDetail -> {
                            navHostController.navigate(
                                Routes.CharacterDetail.navigateTo(
                                    it.id,
                                )
                            )
                        }
                    }
                }
            }

            CharactersScreen(
                charactersListState = characterListState,
                onClick = {
                    viewModel.sendEffect(
                        CharacterListEffect.NavigateToCharacterDetail(it)
                    )
                },
                loadMore = {
                    viewModel.sendIntent(
                        CharacterListIntent.FetchCharacterList(
                            characterListState.info.next,
                        )
                    )
                }
            )
        }

        composable(Routes.CharacterDetail.fullRoute(), arguments = Routes.CharacterDetail.navParams()) {

        }
    }
}