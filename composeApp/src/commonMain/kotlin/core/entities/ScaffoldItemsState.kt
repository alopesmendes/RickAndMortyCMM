package core.entities

import androidx.compose.runtime.Immutable
import core.navigation.Routes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ScaffoldItemsState(
    val navItems: ImmutableList<Routes> = persistentListOf(),
    val startDestination: Routes = Routes.Characters,
    val topBarTitle: String? = null,
    val actionId: Int? = null,
)
