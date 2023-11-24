package com.example.onboarding_presentation.nutrition_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_case.FilterOutDigit
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.onboarding_domain.use_case.validateNutritions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NutritionGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigit: FilterOutDigit,
    private val validateNutritions: validateNutritions
): ViewModel() {

    var state by mutableStateOf(NutritionGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutritionGoalEvent){
        when(event){
            is NutritionGoalEvent.onCarbRatioEnter -> {
                state = state.copy(carbRatio = filterOutDigit(event.ratio))
            }
            is NutritionGoalEvent.onProteinRatioEnter -> {
                state = state.copy(proteinRatio =  filterOutDigit(event.ratio))
            }
            is NutritionGoalEvent.onFatRatioEnter -> {
                state = state.copy(fatRatio =  filterOutDigit(event.ratio))
            }
            is NutritionGoalEvent.OnNextClick -> {
                val result = validateNutritions(
                    carbRatioText = state.carbRatio,
                    proteinRatioText = state.proteinRatio,
                    fatRatioText = state.fatRatio
                )
                when(result){
                    is validateNutritions.Result.Success -> {
                        preferences.SaveCarbRatio(result.carbRatio)
                        preferences.SaveProteinRatio(result.proteinRatio)
                        preferences.SaveFatRatio(result.fatRatio)

                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                    is validateNutritions.Result.Error -> {
                        viewModelScope.launch{
                            _uiEvent.send(UiEvent.showSnackBar(result.message))
                        }
                    }
                }
            }
        }
    }

}