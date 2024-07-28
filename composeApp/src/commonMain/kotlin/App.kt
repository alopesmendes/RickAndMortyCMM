import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import core.entities.ScaffoldItemsState
import core.navigation.NavigationHost
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
            var scaffoldItemsState by remember {
                mutableStateOf(ScaffoldItemsState())
            }

            MainScaffold(
                modifier = Modifier.fillMaxSize(),
                onClick = {
                    navController.navigate(it.navigateTo())
                },
                onNavigateBack = {
                    navController.popBackStack()
                },
                scaffoldItemsState = scaffoldItemsState,
                content = { innerPadding ->
                    NavigationHost(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        navHostController = navController,
                        onScaffoldItemsState = { scaffoldItemsState = it },
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