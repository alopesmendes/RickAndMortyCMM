import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import core.components.MainScaffold
import core.components.MainTopBar
import core.navigation.NavigationHost
import core.navigation.ObserveNavigation
import core.navigation.Routes
import core.navigation.navItemsRoutes
import okio.FileSystem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory {
        getAsyncImageLoader(it)
    }

    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            var navItemsVisible by remember {
                mutableStateOf(true)
            }
            var title by rememberSaveable {
                mutableStateOf("Title")
            }

            ObserveNavigation(
                navController = navController,
                onDestinationChanged = { destination ->
                    navItemsVisible = navItemsRoutes.map { it.fullRoute() }.any { it == destination }
                }
            )

            MainScaffold(
                navItemsVisible = navItemsVisible,
                startDestination = Routes.Characters,
                navItems = navItemsRoutes,
                onClick = {
                    navController.navigate(it.navigateTo())
                },
                topBar = {
                    if (!navItemsVisible) {
                        MainTopBar(
                            title = title,
                            onNavigateBack = if (!navItemsVisible) {
                                {
                                    navController.popBackStack()
                                }
                            } else null,
                        )
                    }
                },
                content = { innerPadding ->
                    NavigationHost(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        navHostController = navController,
                        onTitleChanged = { title = it },
                    )
                }
            )
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .memoryCache {
            MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
        }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
            newDiskCache()
        }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}