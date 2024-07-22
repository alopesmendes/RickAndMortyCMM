package features.characterDetail.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.util.Tools.rememberFlowWithLifecycle
import features.characterDetail.presentation.components.CharacterDetailContent
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
) {
    val state by characterDetailViewModel.state.collectAsStateWithLifecycle()
    val effect = rememberFlowWithLifecycle(characterDetailViewModel.effects)

    LaunchedEffect(effect) {

    }

    CharacterDetailContent(
        modifier = modifier.fillMaxSize(),
        characterDetailState = state
    )
}