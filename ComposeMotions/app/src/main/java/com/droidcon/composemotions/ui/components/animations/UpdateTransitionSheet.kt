package com.droidcon.composemotions.ui.components.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.ToggleOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.droidcon.composemotions.R
import com.droidcon.composemotions.ui.theme.ComposeMotionsTheme

/**
 * Favorite heart heart
 */
enum class Favorite{INITIAL, STARTED}

/**
 * Greeting message state
 */
enum class Greeting{HIDDEN, SHOWN}

/**
 * Duration for drawing the arc
 */
const val DRAW_DURATION = 1000

/**
 * Duration for the appearance of greeting message
 */
const val APPEAR_DURATION = 1000

/**
 * Demo for Jetpack Compose transitions using *updateTransition()* API
 *
 * In short, Transition APIs are used to animate several values together.
 * Two examples are shown here (each in a box). The first one uses [mutableStateOf] to create the transition state
 * used by the API. The second one uses [MutableTransitionState] to create a transition that
 * starts running as soon as it enters composition. That's why the greeting message is displayed
 * when the screen is visited.
 *
 */
@Composable
fun UpdateTransitionSheet() {
    //For first box
    val favoriteState = remember{ mutableStateOf(Favorite.INITIAL) }

    //Create transition object for animating the first box
    val transition = updateTransition(targetState = favoriteState, label = "favorites")

    val checked by remember { derivedStateOf { favoriteState.value == Favorite.STARTED } }

    //Create sweep animation as a child of transition
    val sweep by transition.animateFloat(
        label = "Sweep",
        transitionSpec = { tween(durationMillis = DRAW_DURATION) }
    ) {
        when (it.value) {
            Favorite.INITIAL -> 0f
            Favorite.STARTED -> -360f
        }
    }

    //Create scale animation as child of transition
    val scale by transition.animateFloat(label =  "scale") {
        when (it.value) {
            Favorite.INITIAL -> 1f
            Favorite.STARTED -> 1.2f
        }
    }

    //For second box (MutableTransitionState)
    //MutableTransitionState allows the animation to start running as soon as the transition
    //enters composition
    val greetingState = remember { MutableTransitionState(Greeting.HIDDEN) }
    greetingState.targetState = Greeting.SHOWN


    //Create the transition object for favorite
    val greetingTransition = updateTransition(transitionState = greetingState, label = "Greeting")


    //Create offset animation as a child of transition
    val offset by greetingTransition.animateOffset(
        label = "Offset",
        transitionSpec = {
            when {
                Greeting.HIDDEN isTransitioningTo Greeting.SHOWN -> spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow,
                )
                else -> tween(durationMillis = APPEAR_DURATION, easing = LinearEasing)
            }
        }
    ) {
        when(it) {
            Greeting.HIDDEN -> Offset(0f, 800f)
            Greeting.SHOWN -> Offset(0f, 0f)
        }
    }


    Column(Modifier.fillMaxSize()) {

        //First box
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
                .aspectRatio(1f)
                .fillMaxWidth()) {
            IconToggleButton(checked = checked, onCheckedChange = {favoriteState.value = if (checked) Favorite.INITIAL else Favorite.STARTED}, modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.TopCenter)
            ) {
                //Icon for heart
                Icon(if (checked) Icons.Outlined.ToggleOn else Icons.Outlined.ToggleOn, "Toggle",
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .graphicsLayer {
                            rotationZ = if (checked) 0f else -180f
                        }
                )
            }

            //Canvas for drawing circle around heart
            Canvas(modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(0.8f)
                .align(Alignment.Center)
            ){
                //Draw an arc
                drawArc(
                    Color.Green, startAngle = -90f, sweepAngle = sweep, useCenter = false,
                    style = Stroke(width = 20f)
                )
            }
            //Show a heart
            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = Icons.Outlined.Favorite.name,
                tint = Color.Red, modifier = Modifier
                    .scale(scale)
                    .fillMaxSize(0.5f)
                    .align(Alignment.Center)
            )
        }

        //Second box
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f)
                .fillMaxWidth()
        ){
            Text(text = stringResource(R.string.welcome_to_compose_animations), modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
                .offset { IntOffset(offset.x.toInt(), offset.y.toInt()) },
                style = MaterialTheme.typography.displayMedium
            )

        }

    }
}

@Preview
@Composable
fun UpdateTransitionSheetPreview() {
    ComposeMotionsTheme {
        UpdateTransitionSheet()
    }
}