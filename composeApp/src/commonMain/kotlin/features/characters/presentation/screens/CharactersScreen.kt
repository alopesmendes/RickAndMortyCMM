package features.characters.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.components.MainScaffold
import core.navigation.Routes
import core.navigation.navItemsRoutes
import core.util.Tools.rememberFlowWithLifecycle
import features.characters.presentation.components.CharacterListContent
import features.characters.presentation.components.CharactersListView
import features.characters.presentation.intents.CharacterListEffect
import features.characters.presentation.intents.CharacterListIntent
import features.characters.presentation.state.CharactersListState
import features.characters.presentation.viewModels.CharacterListViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.ui.tooling.preview.Preview
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
                    characterListState.info.next,
                )
            )
        }
    )
}