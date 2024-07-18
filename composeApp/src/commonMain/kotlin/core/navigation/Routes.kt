package core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Routes(
    protected val route: String,
    private val params: List<String> = emptyList(),
) {

    fun fullRoute(): String = "$route${
        params.joinToString(
            separator = "/",
            prefix = "/",
            transform = { "{$it}" }
        )
    }"

    fun navParams(): List<NamedNavArgument> = params.map {
        navArgument(it) {
            type = NavType.StringType
        }
    }

    fun navigateTo(vararg params: String): String = "$route${
        params.joinToString(
            separator = "/",
            prefix = "/"
        )
    }"

    data object Characters: Routes(route = "characters")
}