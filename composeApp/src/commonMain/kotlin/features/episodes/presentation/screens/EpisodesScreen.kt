package features.episodes.presentation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.entities.ScaffoldItemsState
import core.navigation.Routes
import core.navigation.navItemsRoutes
import core.util.Tools.rememberFlowWithLifecycle
import features.episodes.presentation.components.EpisodeListContent
import features.episodes.presentation.intents.EpisodeListEffect
import features.episodes.presentation.intents.EpisodeListIntent
import features.episodes.presentation.viewModels.EpisodeListViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen(
    episodeListViewModel: EpisodeListViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit = {},
) {
    val state by episodeListViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(episodeListViewModel.effects)

    LaunchedEffect(effect) {
        effect.collect {
            when(it) {
                is EpisodeListEffect.NavigateToEpisodeDetail -> {
                    navHostController.navigate(
                        Routes.EpisodeDetail.navigateTo(it.id)
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        onScaffoldItemsState(
            ScaffoldItemsState(
                navItems = navItemsRoutes,
                startDestination = Routes.Episodes,
            )
        )
    }

    EpisodeListContent(
        modifier = modifier,
        episodeListState = state,
        onEpisodeClick = {
            episodeListViewModel.sendEffect(
                EpisodeListEffect.NavigateToEpisodeDetail(it)
            )
        },
        loadMore = {
            episodeListViewModel.sendIntent(
                EpisodeListIntent.FetchEpisodes(
                    page = state.info.next,
                    hasMore = state.episodes.size < state.info.count,
                )
            )
        }
    )

}