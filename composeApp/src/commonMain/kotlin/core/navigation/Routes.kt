package core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationSearching
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import core.util.Constants
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.StringResource
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.characters_item
import rickandmorty.composeapp.generated.resources.episodes_item
import rickandmorty.composeapp.generated.resources.location_item

sealed class Routes(
    val stringRes: StringResource? = null,
    val icon: ImageVector? = null,
    protected val route: String,
    private val params: List<Pair<String, NavType<*>>> = emptyList(),
) {

    fun fullRoute(): String = "$route${
        params.map { it.first }.joinToString(
            separator = "/",
            prefix = "/",
            transform = { "{$it}" }
        )
    }"

    fun navParams(): List<NamedNavArgument> = params.map {
        navArgument(it.first) {
            type = it.second
        }
    }

    fun navigateTo(vararg params: Any): String = "$route${
        params.joinToString(
            separator = "/",
            prefix = "/"
        )
    }"

    data object Characters : Routes(
        route = "characters",
        icon = Icons.Filled.Groups,
        stringRes = Res.string.characters_item,
    )

    data object CharacterDetail : Routes(
        route = "character",
        params = listOf(
            Constants.PARAM_CHARACTER_DETAIL to NavType.IntType,
        )
    )

    data object Episodes : Routes(
        route = "episodes",
        icon = Icons.Filled.VideoLibrary,
        stringRes = Res.string.episodes_item,
    )

    data object Locations : Routes(
        route = "locations",
        icon = Icons.Filled.LocationSearching,
        stringRes = Res.string.location_item,
    )
}

val navItemsRoutes = persistentListOf(Routes.Characters, Routes.Episodes, Routes.Locations)