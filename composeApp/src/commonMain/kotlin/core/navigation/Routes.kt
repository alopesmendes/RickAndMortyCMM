package core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import core.util.Constants

sealed class Routes(
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

    data object Characters : Routes(route = "characters")

    data object CharacterDetail : Routes(
        route = "character",
        params = listOf(
            Constants.PARAM_CHARACTER_DETAIL to NavType.IntType,
        )
    )

}