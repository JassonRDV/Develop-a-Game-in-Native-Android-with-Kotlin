package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.monster.MonsterStage
import com.example.beatfranticallyidle.data.monster.monsterList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MonsterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MonsterStage())
    val uiState: StateFlow<MonsterStage> = _uiState.asStateFlow()

    fun previousMonster() {
        _uiState.update { currentState ->
            currentState.copy(
                currentMonster = monsterList[0],
                lifeMonster = monsterList[0].currentLife,
                maxLifeMonster = monsterList[0].maxLife,
                monsterImage = monsterList[0].image,
            )
        }
    }

    fun nextMonster() {
        _uiState.update { currentState ->
            currentState.copy(
                currentMonster = monsterList[1],
                lifeMonster = monsterList[1].currentLife,
                maxLifeMonster = monsterList[1].maxLife,
                monsterImage = monsterList[1].image,
            )
        }
    }

    fun monsterTookDamage() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.currentMonster.currentLife -= 1f
                currentState.copy(
                    tookDamage = true,
                    lifeMonster = currentState.lifeMonster - 1f
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
        if (_uiState.value.lifeMonster == 0f) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.currentMonster.numberOfDeaths += 1
                    currentState.copy(
                        monsterDead = true,
                        allMonsterDeadCount = currentState.allMonsterDeadCount + 1,
                        rewardValue =
                        currentState.rewardValue + currentState.currentMonster.rewardValue,
                    )
                }
                delay(500)
                _uiState.update { currentState ->
                    currentState.currentMonster.currentLife =
                        currentState.currentMonster.maxLife
                    currentState.copy(
                        monsterDead = false,
                        lifeMonster = currentState.currentMonster.currentLife,
                    )
                }
            }
        }
    }
}