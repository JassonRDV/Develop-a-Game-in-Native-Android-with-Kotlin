package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import com.example.beatfranticallyidle.data.card.HeroInfo
import com.example.beatfranticallyidle.data.card.HeroState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HeroViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HeroState())
    val uiState: StateFlow<HeroState> = _uiState.asStateFlow()

    fun updateListHero(currentList: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentListHero = currentState.allListHero[currentList]
            )
        }
    }

    fun showingCardFullScreen(selectedHero: HeroInfo.Hero) {
        _uiState.update { currentState ->
            currentState.copy(
                showHeroDetails = true,
                currentHero = selectedHero
            )
        }
    }

    fun hideCardFullScreen() {
        _uiState.update { currentState ->
            currentState.copy(
                showHeroDetails = false,
            )
        }
    }

    fun compraCarta() {
        if (_uiState.value.allListHero[0] == _uiState.value.currentListHero) {
            val randomIndex = (0..5).random()
            _uiState.update { currentState ->
                currentState.currentListHero[randomIndex].discovered = true
                currentState.copy(
                    currentHero = currentState.currentListHero[randomIndex],
                )
            }
        }
    }
}