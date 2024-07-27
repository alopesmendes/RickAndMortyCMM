package features.characterDetail.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.navigation.Routes
import core.util.Tools.rememberFlowWithLifecycle
import features.characterDetail.presentation.components.CharacterDetailContent
import features.characterDetail.presentation.intents.CharacterDetailEffect
import features.characterDetail.presentation.viewModels.CharacterDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersHolder

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(
    parametersHolder: ParametersHolder,
    characterDetailViewModel: CharacterDetailViewModel = koinViewModel(
        parameters = { parametersHolder }
    ),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onTitleChanged: (String) -> Unit = {},
) {
    val state by characterDetailViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(characterDetailViewModel.effects)

    LaunchedEffect(effect) {
        effect.collect {
            when(it) {
                is CharacterDetailEffect.NavigateToEpisodeDetail -> {
                    navHostController.navigate(
                        Routes.EpisodeDetail.navigateTo(it.id)
                    )
                }

                is CharacterDetailEffect.NavigateToLocationDetail -> {
                    navHostController.navigate(
                        Routes.LocationDetail.navigateTo(it.id)
                    )
                }
            }
        }
    }

    LaunchedEffect(state) {
        if (!state.isLoading) {
            onTitleChanged(state.character.name)
        }
    }

    CharacterDetailContent(
        modifier = modifier.fillMaxSize(),
        characterDetailState = state,
        onEpisodeClick = {
            characterDetailViewModel.sendEffect(CharacterDetailEffect.NavigateToEpisodeDetail(it))
        },
        onLocationClick = {
            characterDetailViewModel.sendEffect(CharacterDetailEffect.NavigateToLocationDetail(it))
        }
    )
}