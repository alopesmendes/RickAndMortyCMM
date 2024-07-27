package features.locationDetail.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
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
    onTitleChanged: (String) -> Unit = {},
) {
    val locationDetailState by locationDetailViewModel.state.collectAsStateWithLifecycle()
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

    LaunchedEffect(locationDetailState) {
        if (!locationDetailState.isLoading) {
            onTitleChanged(locationDetailState.locationDetail.name)
        }
    }

    LocationDetailContent(
        modifier = modifier.fillMaxSize(),
        locationDetailState = locationDetailState,
        onResidentClick = {
            locationDetailViewModel.sendEffect(
                LocationDetailEffect.NavigateToResident(it)
            )
        }
    )
}