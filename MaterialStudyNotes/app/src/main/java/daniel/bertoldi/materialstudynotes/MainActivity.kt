package daniel.bertoldi.materialstudynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import daniel.bertoldi.materialstudynotes.ui.theme.MaterialStudyNotesTheme
import daniel.bertoldi.materialstudynotes.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialStudyNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        item {
                            Text(text = "Material Study Notes", style = Typography.headlineMedium)
                        }
                        items(makeMockList()) {
                            BaseCard(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BaseCard(
    cardData: CardData,
) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .animateContentSize()
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 8.dp
        ),
        border = BorderStroke(2.dp, Color.Black),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            cardData.title()
            cardData.shortDescription()
            DetailedInfo(isExpanded, cardData.detailedDescription)
            LearnMoreButton(onClick = { isExpanded = !isExpanded }, isExpanded)
        }
    }
}

@Composable
private fun DetailedInfo(
    isExpanded: Boolean,
    description: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = isExpanded,
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(18.dp))
        description()
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider()
    }
}

@Composable
private fun ColumnScope.LearnMoreButton(onClick: () -> Unit, isExpanded: Boolean) {
    Button(
        modifier = Modifier
            .padding(top = 8.dp)
            .align(Alignment.CenterHorizontally),
        onClick = { onClick() },
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 8.dp,
        ),
    ) {
        AnimatedContent(
            targetState = isExpanded,
            transitionSpec = {
                if (targetState) {
                    (slideInHorizontally() + fadeIn()).togetherWith(
                        slideOutHorizontally { height -> -height } + fadeOut())
                } else {
                    (slideInHorizontally { height -> -height } + fadeIn()).togetherWith(
                        slideOutHorizontally { height -> height } + fadeOut())
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            },
            label = "Button Icon Animated Content",
        ) { isExpanded ->
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(18.dp),
                imageVector = if (isExpanded) Icons.Outlined.Edit else Icons.Outlined.Check,
                contentDescription = null,
            )
        }
        Crossfade(targetState = isExpanded, label = "Button Crossfade") {
            when (it) {
                true -> Text(text = "Click to Short It Up!", style = Typography.bodyMedium)
                false -> Text(text = "Click to Know More!", style = Typography.bodyMedium)
            }
        }
    }
}

@Preview(
    showBackground = true,
    apiLevel = 33, // By using BOM 2024.02 the preview breaks if we don't define the API level to 33
)
@Composable
fun BaseCardPreview() {
    MaterialStudyNotesTheme {
        BaseCard(makeMockList()[0])
    }
}

@Preview(
    showBackground = true,
    apiLevel = 33, // By using BOM 2024.02 the preview breaks if we don't define the API level to 33
)
@Composable
fun LearnMoreButtonPreview() {
    MaterialStudyNotesTheme {
        var isExpanded by remember { mutableStateOf(false) }
        Column {
            LearnMoreButton({isExpanded = !isExpanded}, isExpanded)
        }
    }
}