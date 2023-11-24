package com.example.tracker_presentation.tracker_overView.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.R
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientsBarInfo(
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp
) {

    val spacing = LocalSpacing.current

    val background = MaterialTheme.colorScheme.background
    val goalExceedColor = MaterialTheme.colorScheme.error
    val angleRatio = remember{ Animatable(0f) }

    LaunchedEffect(key1 = value){
        angleRatio.animateTo(
            targetValue = if (goal > 0){
                value / goal.toFloat()
            }else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ){
            drawArc(
                color = if (value <= goal) background else goalExceedColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                size = size,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
            if (value <= goal ){
                drawArc(
                    color = color,
                    startAngle = 90f,
                    sweepAngle = 360f * angleRatio.value,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Round
                    )

                )
            }
        }

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UnitDisplay(
                amount = value,
                unit = stringResource(id = R.string.grams),
                amountColor = if (value <= goal){
                    MaterialTheme.colorScheme.onPrimary
                }else goalExceedColor,
                unitColor = if (value <= goal){
                    MaterialTheme.colorScheme.onPrimary
                }else goalExceedColor
            )

            Spacer(modifier = Modifier.height(spacing.spaceSmall))

            Text(
                text = name,
                color = if (value <= goal){
                    MaterialTheme.colorScheme.onPrimary
                }else goalExceedColor,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Light
            )
        }

    }

}