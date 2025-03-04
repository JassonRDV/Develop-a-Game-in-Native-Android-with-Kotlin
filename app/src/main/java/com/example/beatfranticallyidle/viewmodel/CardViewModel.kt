package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import com.example.beatfranticallyidle.data.source.local.card.HeroInfo
import com.example.beatfranticallyidle.data.source.local.card.listAllHeroes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<CardUiState> = MutableStateFlow(CardUiState())
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    fun bottomBarTypeHero(currentList: Int) {
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

    fun buyCard() {
//        if (_uiState.value.allListHero[0] == _uiState.value.currentListHero
//            && _uiState.value.totalReward >= _uiState.value.purchaseCost
//        ) {
//            val randomIndex = (0..5).random()
//            _uiState.update { currentState ->
//                currentState.currentListHero[randomIndex].discovered = true
//                currentState.currentListHero[randomIndex].numberCardCount += 1
//                currentState.totalReward -= currentState.purchaseCost
//                currentState.purchaseCost *= currentState.increasedPurchaseCost
//                currentState.copy(
//                    currentHero = currentState.currentListHero[randomIndex]
//                )
//            }
//        }
//        if (
//            _uiState.value.currentListHero[0].discovered &&
//            !_uiState.value.currentListHero[0].effectActivated
//        ) {
//            _uiState.update { currentState ->
//                currentState.currentListHero[0].effectActivated = true
//                currentState.copy()
//            }
//            spiritEffect()
//        }
    }

    private fun spiritEffect() {
//        viewModelScope.launch {
//            while (_uiState.value.allListHero[0][0].discovered) {
//                monsterTookDamage()
//                delay(1000)
//            }
//        }
    }
}

data class CardUiState(
    // Lista de heróis e herói atual
    val allListHero: List<List<HeroInfo.Hero>> = listAllHeroes,
    val currentListHero: List<HeroInfo.Hero> = allListHero[0],
    val currentHero: HeroInfo.Hero = currentListHero[0],

    // Estado do herói atual
    val showHeroDetails: Boolean = false,
    var purchaseCost: Float = 10f,
    val increasedPurchaseCost: Float = 1.25f
)