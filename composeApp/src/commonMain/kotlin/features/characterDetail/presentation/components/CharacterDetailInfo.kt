package features.characterDetail.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Healing
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.Transgender
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import features.characterDetail.presentation.state.CharacterDetailItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.gender
import rickandmorty.composeapp.generated.resources.info
import rickandmorty.composeapp.generated.resources.species
import rickandmorty.composeapp.generated.resources.status

@Composable
fun CharacterDetailInfo(
    modifier: Modifier = Modifier,
    characterDetailItem: CharacterDetailItem,
) {
    CharacterDetailTitleWithContent(
        modifier = modifier,
        title = stringResource(Res.string.info),
        content = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 0.dp,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.primarySurface
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    CharacterDetailInfoItem(
                        icon = Icons.Outlined.Transgender,
                        label = stringResource(Res.string.gender),
                        data = characterDetailItem.gender,
                    )
                    Divider()
                    CharacterDetailInfoItem(
                        icon = Icons.Outlined.Healing,
                        label = stringResource(Res.string.status),
                        data = characterDetailItem.status,
                    )
                    Divider()
                    CharacterDetailInfoItem(
                        icon = Icons.Outlined.Pets,
                        label = stringResource(Res.string.species),
                        data = characterDetailItem.species,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CharacterDetailInfoPreview() {
    CharacterDetailInfo(
        characterDetailItem = CharacterDetailItem(
            id = 5787,
            name = "Belinda Mendez",
            gender = "eleifend",
            image = "sit",
            status = "maecenas",
            species = "diam",
        )
    )
}