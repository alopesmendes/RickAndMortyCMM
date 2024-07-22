package core.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TitleComponent(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.h5,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primarySurface,
    )
}

@Preview
@Composable
fun TitleComponentPreview() {
    var expanded by remember { mutableStateOf(false) }
    TitleComponent(
        title = "Hello"
    )
}