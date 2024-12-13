package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.card.HeroInfo
import com.example.beatfranticallyidle.data.IdleStage
import com.example.beatfranticallyidle.data.monster.monsterList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IdleViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(IdleStage())
    val uiState: StateFlow<IdleStage> = _uiState.asStateFlow()

    fun previousMonster() {
        _uiState.update { currentState ->
            currentState.copy(
                currentMonster = monsterList[0]
            )
        }
    }

    fun nextMonster() {
        _uiState.update { currentState ->
            currentState.copy(
                currentMonster = monsterList[1]
            )
        }
    }

    fun monsterTookDamage() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.currentMonster.currentLife -= 1f
                currentState.copy(
                    tookDamage = true,
                )
            }
            delay(50)
            _uiState.update { currentState ->
                currentState.copy(
                    tookDamage = false
                )
            }
            monsterDied()
        }
    }

    private fun monsterDied() {
        if (_uiState.value.currentMonster.currentLife == 0f) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.currentMonster.numberOfDeaths += 1
                    currentState.copy(
                        monsterDead = true,
                        numberAllDeath = currentState.numberAllDeath + 1,
                        totalReward =
                        currentState.totalReward + currentState.currentMonster.rewardValue,
                    )
                }
                delay(500)
                _uiState.update { currentState ->
                    currentState.currentMonster.currentLife = currentState.currentMonster.maxLife
                    currentState.copy(
                        monsterDead = false,
                    )
                }
            }
        }
    }

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
        if (_uiState.value.allListHero[0] == _uiState.value.currentListHero
            && _uiState.value.totalReward >= _uiState.value.purchaseCost
        ) {
            val randomIndex = (0..5).random()
            _uiState.update { currentState ->
                currentState.currentListHero[randomIndex].discovered = true
                currentState.currentListHero[randomIndex].numberCardCount += 1
                currentState.totalReward -= currentState.purchaseCost
                currentState.purchaseCost *= currentState.increasedPurchaseCost
                currentState.copy(
                    currentHero = currentState.currentListHero[randomIndex]
                )
            }
        }
        if (
            _uiState.value.currentListHero[0].discovered &&
            !_uiState.value.currentListHero[0].effectActivated
        ) {
            _uiState.update { currentState ->
                currentState.currentListHero[0].effectActivated = true
                currentState.copy()
            }
            spiritEffect()
        }
    }

    private fun spiritEffect() {
        viewModelScope.launch {
            while (uiState.value.allListHero[0][0].discovered) {
                monsterTookDamage()
                delay(1000)
            }
        }
    }
}
