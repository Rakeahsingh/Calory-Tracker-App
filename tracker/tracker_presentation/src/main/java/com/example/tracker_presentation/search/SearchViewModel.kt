package com.example.tracker_presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.use_case.FilterOutDigit
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import com.example.tracker_domain.use_case.TrackerUseCase
import com.example.core.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCase: TrackerUseCase,
    private val filterOutDigit: FilterOutDigit
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(
                  trackableFood  = state.trackableFood.map {
                    if (it.food == event.food){
                        it.copy(
                            amount = filterOutDigit(event.amount)
                        )
                    } else it
                  }
                )
            }
            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFood = state.trackableFood.map {
                        if (it.food == event.food){
                            it.copy(
                                isExpended = !it.isExpended
                            )
                        } else it
                    }
                )
            }
            is SearchEvent.OnTrackableFoodClick -> {
                trackFood(event)
            }
            else -> Unit
        }
    }

    private fun executeSearch() {
        viewModelScope.launch{
            state = state.copy(
                isSearching = true,
                trackableFood = emptyList()
            )
            trackerUseCase
                .searchFood(state.query)
                .onSuccess {
                    state = state.copy(
                        trackableFood = it.map {
                            TrackableFoodUiState(it)
                        },
                        isSearching = false,
                        query = ""
                    )
                }
                .onFailure {
                    state = state.copy(
                        isSearching = false
                    )
                    _uiEvent.send(
                        UiEvent.showSnackBar(
                            message = UiText.StringResources(R.string.error_something_went_wrong
                            )
                        )
                    )
                }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackableFoodClick) {
        viewModelScope.launch {
            val uiState = state.trackableFood.find {
                it.food == event.food
            }
            trackerUseCase.trackFood(
                food = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
}