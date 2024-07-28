package core.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
private fun BackNavigationIcon(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            contentDescription = null
        )
    }
}

@Composable
fun MainTopBar(
    title: String,
    actionId: Int? = null,
    onNavigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
            )
        },
        navigationIcon = if (onNavigateBack != null) {
            {
                BackNavigationIcon(
                    onClick = onNavigateBack,
                )
            }
        } else null,
        actions = {
            actionId?.let {
                Text("#$it")
            }
        }
    )
}