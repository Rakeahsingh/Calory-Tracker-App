package com.example.tracker_presentation.tracker_overView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientInfo(
    name: String,
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colorScheme.onBackground,
    unitTextSize: TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colorScheme.onBackground,
    nameTextStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {

    val spacing = LocalSpacing.current

   Column(
       modifier = modifier,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       UnitDisplay(
           amount = amount,
           unit =unit,
           amountTextSize = amountTextSize,
           unitTextSize = unitTextSize,
           amountColor = amountColor,
           unitColor = unitColor
       )

       Spacer(modifier = Modifier.height(spacing.spaceSmall))

       Text(
           text = name,
           style = nameTextStyle,
           color = MaterialTheme.colorScheme.onBackground,
           fontWeight = FontWeight.Light
       )
   }

}