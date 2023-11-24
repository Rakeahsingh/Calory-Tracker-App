package com.example.tracker_presentation.tracker_overView.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.tracker_overView.Meal
import com.example.tracker_presentation.tracker_overView.NutrientInfo
import androidx.compose.foundation.Image as Image

@Composable
fun ExpandableMeal(
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {

        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )

            Spacer(modifier = Modifier.width(spacing.spaceMedium))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Icon(
                        imageVector = if (meal.isExpended){
                            Icons.Default.KeyboardArrowUp
                        }else Icons.Default.KeyboardArrowDown
                             ,
                        contentDescription = if (meal.isExpended){
                            stringResource(id = R.string.collapse)
                        }else stringResource(id = R.string.extend)
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceSmall))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    UnitDisplay(
                        amount = meal.calories,
                        unit = stringResource(id = R.string.kcal),
                        amountTextSize = 25.sp
                    )

                    Row {
                        NutrientInfo(
                            name = stringResource(id = R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(id = R.string.grams),
                            amountTextSize = 15.sp,
                            unitTextSize = 10.sp
                        )

                        Spacer(modifier = Modifier.width(spacing.spaceSmall))

                        NutrientInfo(
                            name = stringResource(id = R.string.protein),
                            amount = meal.protein,
                            unit = stringResource(id = R.string.grams),
                            amountTextSize = 15.sp,
                            unitTextSize = 10.sp
                        )

                        Spacer(modifier = Modifier.width(spacing.spaceSmall))

                        NutrientInfo(
                            name = stringResource(id = R.string.fat),
                            amount = meal.fat,
                            unit = stringResource(id = R.string.grams),
                            amountTextSize = 15.sp,
                            unitTextSize = 10.sp
                        )
                        
                    }

                }

            }

        }
        
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        AnimatedVisibility(visible = meal.isExpended) {
            content()
        }

    }
}