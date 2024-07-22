package features.locations.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import core.components.MainScaffold
import core.navigation.Routes
import core.navigation.navItemsRoutes

@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Text("Locations", modifier = modifier)
}