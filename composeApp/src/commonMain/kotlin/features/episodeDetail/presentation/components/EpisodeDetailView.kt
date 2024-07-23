package features.episodeDetail.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.TitleComponent
import features.episodeDetail.presentation.state.EpisodeDetailItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EpisodeDetailView(
    modifier: Modifier = Modifier,
    episodeDetail: EpisodeDetailItem,
) {
    Column(modifier = modifier) {
        TitleComponent(
            modifier = Modifier.fillMaxWidth(),
            title = episodeDetail.name
        )
        Spacer(Modifier.height(4.dp))

        Text(
            text = episodeDetail.airDate,
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle1,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = episodeDetail.episode,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview
@Composable
fun EpisodeDetailViewPreview() {
    EpisodeDetailView(
        episodeDetail = EpisodeDetailItem(
            id = 6990,
            name = "Gilda Sanders",
            episode = "corrumpit",
            airDate = "posuere"
        )
    )
}