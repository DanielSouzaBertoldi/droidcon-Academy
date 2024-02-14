package daniel.bertoldi.materialstudynotes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import daniel.bertoldi.materialstudynotes.ui.theme.Typography

data class CardData(
    val title: @Composable () -> Unit,
    val shortDescription: @Composable () -> Unit,
    val detailedDescription: @Composable () -> Unit,
)

fun makeMockList() = listOf(
    CardData(
        title = {
            Text(text = "Item 1", fontSize = 24.sp, style = Typography.titleLarge)
        },
        shortDescription = {
            Text(text = "Short Description 1", fontSize = 16.sp, style = Typography.bodySmall, fontStyle = FontStyle.Italic)
        },
        detailedDescription = {
            Text(text = "Detailed Description 1", style = Typography.bodyMedium)
        }
    ),
    CardData(
        title = {
            Text(text = "Item 2", fontSize = 24.sp, style = Typography.titleLarge)
        },
        shortDescription = {
            Text(text = "Short Description 2", fontSize = 16.sp, style = Typography.bodySmall, fontStyle = FontStyle.Italic)
        },
        detailedDescription = {
            Text(text = "Detailed Description 2", style = Typography.bodyMedium)
        }
    ),
    CardData(
        title = {
            Text(text = "Item 3", fontSize = 24.sp, style = Typography.titleLarge)
        },
        shortDescription = {
            Text(text = "Short Description 3", fontSize = 16.sp, style = Typography.bodySmall, fontStyle = FontStyle.Italic)
        },
        detailedDescription = {
            Text(text = "Detailed Description 3", style = Typography.bodyMedium)
        }
    ),
    CardData(
        title = {
            Text(text = "Item 4", fontSize = 24.sp, style = Typography.titleLarge)
        },
        shortDescription = {
            Text(text = "Short Description 4", fontSize = 16.sp, style = Typography.bodySmall, fontStyle = FontStyle.Italic)
        },
        detailedDescription = {
            Text(text = "Detailed Description 4", style = Typography.bodyMedium)
        }
    ),CardData(
        title = {
            Text(text = "Item 5", fontSize = 24.sp, style = Typography.titleLarge)
        },
        shortDescription = {
            Text(text = "Short Description 5", fontSize = 16.sp, style = Typography.bodySmall, fontStyle = FontStyle.Italic)
        },
        detailedDescription = {
            Text(text = "Detailed Description 5", style = Typography.bodyMedium)
        }
    ),
)