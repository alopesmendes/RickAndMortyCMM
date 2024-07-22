package features.characters.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.navigation.Routes
import core.util.Tools.rememberFlowWithLifecycle
import features.characters.presentation.components.CharacterListContent
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.intents.CharacterListIntent
import features.characters.presentation.viewModels.CharacterListViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(
    charactersListViewModel: CharacterListViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val characterListState by charactersListViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(charactersListViewModel.effects)

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

    CharacterListContent(
        modifier = modifier,
        charactersListState = characterListState,
        onClick = {
            charactersListViewModel.sendEffect(
                CharacterListEffect.NavigateToCharacterDetail(it)
            )
        },
        loadMore = {
            charactersListViewModel.sendIntent(
                CharacterListIntent.FetchCharacterList(
                    page = characterListState.info.next,
                    hasMore = characterListState.characters.size < characterListState.info.count
                )
            )
        }
    )
}