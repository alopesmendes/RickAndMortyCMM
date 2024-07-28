package features.locationDetail.presentation.screens

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
import features.locationDetail.presentation.components.LocationDetailContent
import features.locationDetail.presentation.intents.LocationDetailEffect
import features.locationDetail.presentation.viewModels.LocationDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.ParametersHolder

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LocationDetailScreen(
    parametersHolder: ParametersHolder,
    locationDetailViewModel: LocationDetailViewModel = koinViewModel(
        parameters = { parametersHolder }
    ),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onScaffoldItemsState: (ScaffoldItemsState) -> Unit = {},
) {
    val state by locationDetailViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(locationDetailViewModel.effects)

    LaunchedEffect(effect) {
        effect.collect {
            when (it) {
                is LocationDetailEffect.NavigateToResident -> {
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
                    topBarTitle = state.locationDetail.name,
                    actionId = state.locationDetail.id,
                    startDestination = Routes.CharacterDetail,
                )
            )
        }
    }

    LocationDetailContent(
        modifier = modifier.fillMaxSize(),
        locationDetailState = state,
        onResidentClick = {
            locationDetailViewModel.sendEffect(
                LocationDetailEffect.NavigateToResident(it)
            )
        }
    )
}