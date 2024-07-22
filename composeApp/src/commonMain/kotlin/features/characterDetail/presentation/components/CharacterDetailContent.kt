package features.characterDetail.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.characterDetail.presentation.state.CharacterDetailItem
import features.characterDetail.presentation.state.CharacterDetailState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    characterDetailState: CharacterDetailState
) {
    val scrollState = rememberScrollState()

    when {
        characterDetailState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        characterDetailState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = characterDetailState.error, color = MaterialTheme.colors.error)
            }
        }

        else -> {
            val characterDetailItem = remember { characterDetailState.character }
            Column(
                modifier = modifier.verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CharacterDetailImage(
                    imageUrl = characterDetailItem.image,
                    modifier = Modifier.weight(1f).padding(8.dp),
                )
                CharacterDetailInfo(
                    modifier = Modifier.weight(1f),
                    characterDetailItem = characterDetailItem,
                )
            }
        }
    }

}

@Preview
@Composable
fun CharacterDetailContentPreview() {
    CharacterDetailContent(
        characterDetailState = CharacterDetailState(
            isLoading = false,
            character = CharacterDetailItem(
                id = 4626,
                name = "Morgan Woods",
                gender = "euismod",
                image = "appareat",
                status = "explicari",
                species = "intellegebat"
            )
        )
    )
}