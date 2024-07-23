package features.episodeDetail.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.util.Tools.rememberFlowWithLifecycle
import features.episodeDetail.presentation.components.EpisodeDetailContent
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
) {
    val state by episodeDetailViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(episodeDetailViewModel.effects)

    LaunchedEffect(effect) {
        effect.collect {

        }
    }

    EpisodeDetailContent(
        modifier = modifier.fillMaxSize(),
        episodeDetailState = state,
    )
}