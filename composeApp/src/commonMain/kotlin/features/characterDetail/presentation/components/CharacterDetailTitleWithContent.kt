package features.characterDetail.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.components.TitleComponent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CharacterDetailTitleWithContent(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
) {
    var visible by remember { mutableStateOf(true) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clickable { visible = !visible },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleComponent(title = title)
            IconButton(
                onClick = { visible = !visible },
            ) {
                Icon(
                    imageVector = if (visible) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primaryVariant,
                )
            }
        }

        AnimatedVisibility(visible) {
            content()
        }
    }
}

@Preview
@Composable
fun CharacterDetailTitleWithContentPreview() {
    CharacterDetailTitleWithContent(title = "Title") {
        Text(text = "Content")
    }
}