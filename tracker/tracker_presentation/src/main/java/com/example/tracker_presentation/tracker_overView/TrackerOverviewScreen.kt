package com.example.tracker_presentation.tracker_overView

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.tracker_presentation.tracker_overView.components.AddButton
import com.example.tracker_presentation.tracker_overView.components.ExpandableMeal
import com.example.tracker_presentation.tracker_overView.components.NutrientsHeader
import com.example.tracker_presentation.tracker_overView.components.TrackedFoodItem

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context){
        viewModel.uiEvent.collect{event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = spacing.spaceMedium
            )
    ){
        item {
            NutrientsHeader(state = state)

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviewDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier
                    .padding(horizontal = spacing.spaceMedium)
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }

        items(state.meals){meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                },
                content = {
                          Column(
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .padding(horizontal = spacing.spaceSmall)
                          ) {
                              state.trackedFoods.forEach {food ->
                                  TrackedFoodItem(
                                      trackedFood = food,
                                      onDeleteClick = {
                                          viewModel.onEvent(TrackerOverviewEvent.OnDeleteTrackedFood(food))
                                      }
                                  )
                                  Spacer(modifier = Modifier.height(spacing.spaceMedium))
                              }

                              AddButton(
                                  text = stringResource(
                                      id = R.string.add_meal,
                                      meal.name.asString(context)
                                  ),
                                  onClick = {
                                      viewModel.onEvent(
                                          TrackerOverviewEvent.OnAddFoodClick(meal)
                                      )
                                  },
                                  modifier = Modifier.fillMaxWidth()
                              )

                          }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}