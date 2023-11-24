package com.example.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colorScheme.onBackground,
    unitTextSize: TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colorScheme.onBackground
) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
    ) {
        Text(
            text = amount.toString(),
            fontSize = amountTextSize,
            style = MaterialTheme.typography.headlineLarge,
            color = amountColor,
            modifier = modifier.alignBy(LastBaseline)
        )

        Spacer(modifier = modifier.width(spacing.spaceExtraSmall))

        Text(
            text = unit,
            fontSize = unitTextSize,
            style = MaterialTheme.typography.bodyMedium,
            color = unitColor,
            modifier = modifier.alignBy(LastBaseline)
        )
    }

}