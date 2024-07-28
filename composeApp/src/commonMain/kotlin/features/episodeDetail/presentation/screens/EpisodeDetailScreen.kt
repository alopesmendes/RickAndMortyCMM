package features.episodeDetail.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.entities.ScaffoldItemsState
import core.navigation.Routes
import core.util.Tools.rememberFlowWithLifecycle
import features.episodeDetail.presentation.components.EpisodeDetailContent
import features.episodeDetail.presentation.intents.EpisodeDetailEffect
import features.episodeDetail.presentation.viewModels.EpisodeDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersHolder

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodeDetailScreen(
    parametersHolder: ParametersHolder,
    episodeDetailViewModel: EpisodeDetailViewModel = koinViewModel(
        parameters = { parametersHolder }
    ),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit = {},
) {
    val state by episodeDetailViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(episodeDetailViewModel.effects)

    LaunchedEffect(effect) {
        effect.collect {
            when(it) {
                is EpisodeDetailEffect.NavigateToCharacterDetail -> {
                    navHostController.navigate(
                        Routes.CharacterDetail.navigateTo(it.id)
                    )
                }
            }
        }
    }

    LaunchedEffect(state) {
        if (!state.isLoading) {
            onScaffoldItemsState(
                ScaffoldItemsState(
                    topBarTitle = state.episodeDetail.name,
                    actionId = state.episodeDetail.id,
                    startDestination = Routes.EpisodeDetail,
                )
            )
        }
    }

    EpisodeDetailContent(
        modifier = modifier.fillMaxSize(),
        episodeDetailState = state,
        onCharacterClick = {
            episodeDetailViewModel.sendEffect(
                EpisodeDetailEffect.NavigateToCharacterDetail(it)
            )
        }
    )
}