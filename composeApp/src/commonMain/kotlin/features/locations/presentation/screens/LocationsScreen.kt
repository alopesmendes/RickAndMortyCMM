package features.locations.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.navigation.Routes
import core.util.Tools.rememberFlowWithLifecycle
import features.locations.presentation.components.LocationListContent
import features.locations.presentation.intents.LocationListEffect
import features.locations.presentation.intents.LocationListIntent
import features.locations.presentation.viewModels.LocationsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LocationsScreen(
    locationsViewModel: LocationsViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    val state by locationsViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(locationsViewModel.effects)

    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                is LocationListEffect.NavigationToLocationDetail -> {
                    navHostController.navigate(
                        Routes.Locations.navigateTo(it.id)
                    )
                }
            }
        }
    }

    LocationListContent(
        modifier = modifier,
        state = state,
        onLocationClick = {
            locationsViewModel.sendEffect(
                LocationListEffect.NavigationToLocationDetail(it)
            )
        },
        loadMore = {
            locationsViewModel.sendIntent(
                LocationListIntent.FetchLocations(
                    page = state.info.next,
                    hasMore = state.locations.size < state.info.count
                )
            )
        }
    )
}