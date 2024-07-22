package features.characterDetail.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.TitleComponent
import features.characterDetail.presentation.state.CharacterLocationItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import rickandmorty.composeapp.generated.resources.Res
import rickandmorty.composeapp.generated.resources.location

@Composable
fun CharacterDetailLocation(
    modifier: Modifier = Modifier,
    location: CharacterLocationItem,
    origin: CharacterLocationItem,
    onLocationClick: (Int) -> Unit,
) {
    CharacterDetailTitleWithContent(
        modifier = modifier,
        title = stringResource(Res.string.location),
        content = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp,
            ) {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CharacterDetailLocationItem(
                        icon = Icons.Outlined.Home,
                        name = origin.name,
                        onClick = { origin.id?.let { onLocationClick(it) } },
                        modifier = Modifier.weight(1f)
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    CharacterDetailLocationItem(
                        icon = Icons.Outlined.TravelExplore,
                        name = location.name,
                        onClick = { location.id?.let { onLocationClick(it) } },
                        modifier = Modifier.weight(1f),
                        arrangement = Arrangement.End,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun CharacterDetailLocationPreview() {
    CharacterDetailLocation(
        location = CharacterLocationItem(
            name = "Citadel of Ricks",
            id = 1
        ),
        origin = CharacterLocationItem(
            name = "Earth (C-137)",
            id = 2,
        ),
        onLocationClick = {}
    )
}