package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import com.example.beatfranticallyidle.data.card.CardInfo
import com.example.beatfranticallyidle.data.card.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CartState())
    val uiState: StateFlow<CartState> = _uiState.asStateFlow()

    fun effectActivated() {
        _uiState.update { currentState ->
            currentState.copy(
                effectActivated = true
            )
        }
    }

    fun showingCardFullScreen(selectedCard: CardInfo.Card) {
        _uiState.update { currentState ->
            currentState.copy(
                cardFullScreen = true,
                cardFullScreenCurrent = selectedCard
            )
        }
    }

    fun hideCardFullScreen() {
        _uiState.update { currentState ->
            currentState.copy(
                cardFullScreen = false,
            )
        }
    }
}