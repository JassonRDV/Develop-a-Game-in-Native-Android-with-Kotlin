package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.source.local.monster.MonsterRepository
import com.example.beatfranticallyidle.data.source.local.monster.listMonsterEntity
import com.example.beatfranticallyidle.data.source.local.monster.model.GenericMonster
import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MonsterUiStage(
    val isLoading: Boolean = true,
    val monsterList: List<Monster> = emptyList(),
    val currentMonster: Monster = GenericMonster,
    val currentMonsterIndex: Int = 0,
    val tookDamage: Boolean = false,
    val monsterDead: Boolean = false,
    var totalReward: Float = 0f, // TODO criar um ViewModel para isso
    val totalDeath: Int = 0, // TODO criar um ViewModel para isso
    val errorMonster: String? = null
)

@HiltViewModel
class MonsterViewModel @Inject constructor(
    private val monsterRepository: MonsterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MonsterUiStage())
    val uiState: StateFlow<MonsterUiStage> = _uiState.asStateFlow()

    init {
        loadMonster()
    }

    /**
     * Loads monsters from the repository, updating the UI state with the results.
     **/
    private fun loadMonster() {
        _uiState.update { it.copy(isLoading = true, errorMonster = null) }
        viewModelScope.launch {
            try {
                monsterRepository.getMonsters().collectLatest { monsterList ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            monsterList = monsterList,
                            currentMonster = monsterList.firstOrNull() ?: GenericMonster,
                            isLoading = false,
                            errorMonster = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false, errorMonster = "Error loading monsters: ${e.message}"
                    )
                }
            }
        }
    }

    fun previousMonster() {
        val newIndex = (_uiState.value.currentMonsterIndex - 1).coerceAtLeast(0)
        updateCurrentMonster(newIndex)
    }

    fun nextMonster() {
        val newIndex =
            (_uiState.value.currentMonsterIndex + 1).coerceAtMost(_uiState.value.monsterList.lastIndex)
        updateCurrentMonster(newIndex)
    }

    private fun updateCurrentMonster(newIndex: Int) {
        val monsterList = _uiState.value.monsterList
        if (newIndex in monsterList.indices) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentMonster = monsterList[newIndex],
                    currentMonsterIndex = newIndex,
                    errorMonster = null
                )
            }
        } else {
            _uiState.update { it.copy(errorMonster = "Invalid Monster Index") }
        }
    }

    /* TODO vou ter que centralizar todas as fontes de dano e alem de fazer essa função
    * TODO seja chamada todas as vezes que o monstro toma dano
    */
    fun monsterTookDamage() {
        val currentMonster = _uiState.value.currentMonster
        if (currentMonster.isAlive()) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(
                        tookDamage = true,
                        currentMonster = currentMonster.takeDamage(1f)
                    )
                }
                delay(50)
                _uiState.update { currentState ->
                    currentState.copy(
                        tookDamage = false,
                    )
                }
                if (!currentMonster.isAlive()) {
                    monsterDied()
                }
            }
        }
    }

    private fun monsterDied() {
        val currentMonster = _uiState.value.currentMonster
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    monsterDead = true,
                    currentMonster = currentMonster.die()
                )
            }
            delay(200)
            _uiState.update { currentState ->
                currentState.copy(
                    monsterDead = false,
                )
            }
        }
    }

    // I will only use it to start the database
    fun insertAllMonsters(allMonsters: List<Monster> = listMonsterEntity) {
        viewModelScope.launch {
            val monsterList = monsterRepository.getMonsters().firstOrNull()
            if (monsterList.isNullOrEmpty()) {
                monsterRepository.insertAll(allMonsters)
            }
        }
    }
}