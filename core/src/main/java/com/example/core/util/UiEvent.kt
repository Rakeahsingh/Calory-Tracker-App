package com.example.core.util

sealed class UiEvent{

    data class Navigate(val route: String): UiEvent()

    object NavigateUp: UiEvent()

    data class showSnackBar(val message: UiText): UiEvent()

}
