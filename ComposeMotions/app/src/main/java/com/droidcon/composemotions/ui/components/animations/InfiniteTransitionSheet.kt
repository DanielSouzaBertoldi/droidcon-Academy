package com.droidcon.composemotions.ui.components.animations

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.droidcon.composemotions.R
import com.droidcon.composemotions.ui.theme.ComposeMotionsTheme

const val COLOR_ANIMATION_DURATION = 2000

const val TEXT_MOVE_DURATION = 10000

/**
 * Demo for infinite animations in Jetpack Compose
 * This demo shows implementation of two infinite animations. One for the background and the other
 * for the offset of a text that moves across the screen. Note the custom type converter used for animating
 * the text offset. That's because Compose does not support Offset animation natively, so a custom
 * type converter has been used to convert the offset values to an [AnimationVector2D] type that the
 * animation system understands.
 *
 */
@Composable
fun InfiniteTransitionSheet() {
    //Define the infinite animation
    val infinite = rememberInfiniteTransition()

    //Define animating background color
    val bgColor by infinite.animateColor(
        initialValue = MaterialTheme.colorScheme.secondaryContainer,
        targetValue = MaterialTheme.colorScheme.tertiary,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = COLOR_ANIMATION_DURATION)
        ),
    )

    //Get the width of the screen
    val width = LocalConfiguration.current.screenWidthDp.toFloat()

    //Offset for the text
    val animateOffSet by infinite.animateValue(
        initialValue = Offset(width, 100f),
        targetValue = Offset(-2 * width, 100f),
        typeConverter = TwoWayConverter(
            convertToVector = { AnimationVector2D(it.x, it.y) },
            convertFromVector = { Offset(it.v1, it.v2) },
        ),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = TEXT_MOVE_DURATION, easing = LinearEasing),
        ),
    )



    Box(
        Modifier
            .fillMaxSize()
            .drawBehind {
                // drawBehind is called in the draw phase and skips recomposition and layout
                // phases each time the color value is updated. Hence, it improves performance.
                drawRect(bgColor)
            }

    ){
        Text(text = stringResource(R.string.please_take_care_of_your_code_by_documenting_it),
            modifier = Modifier
                .width(1000.dp)
                .offset { IntOffset(animateOffSet.x.toInt(), animateOffSet.y.toInt()) },
            maxLines = 1, //We want only one line that moves horizontally
            overflow = TextOverflow.Visible, //No clipping or ellipsis
            softWrap = false //Make the line long to have a marquee
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InfiniteTransitionSheetPreview() {
    ComposeMotionsTheme {
        InfiniteTransitionSheet()
    }
}