package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.source.local.monster.MonsterRepository
import com.example.beatfranticallyidle.data.source.local.monster.listMonsterEntity
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

@HiltViewModel
class MonsterViewModel @Inject constructor(
    private val monsterRepository: MonsterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MonsterUiStage())
    val uiState: StateFlow<MonsterUiStage> = _uiState.asStateFlow()

    init {
        loadMonster()
    }

    private fun loadMonster() {
        _uiState.update {
            it.copy(
                isLoading = true,
                errorMonster = null
            )
        }
        viewModelScope.launch {
            try {
                monsterRepository.getMonsters().collectLatest { monsterList ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            monsterList = monsterList,
                            currentMonster = monsterList[currentState.currentMonsterIndex ?: 0],
                            currentMonsterIndex = 0,
                            isLoading = false,
                            errorMonster = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMonster = "Error loading monsters: ${e.message}"
                    )
                }
            }
        }
    }

    fun previousMonster() {
        updateMonsterUiStage(
            _uiState.value.currentMonsterIndex
                ?.minus(1)
                ?.coerceAtLeast(0) ?: 0
        )
    }

    fun nextMonster() {
        updateMonsterUiStage(
            _uiState.value.currentMonsterIndex
                ?.plus(1)
                ?.coerceAtMost(_uiState.value.monsterList.lastIndex) ?: 0
        )
    }

    private fun updateMonsterUiStage(newIndex: Int) {
        val monsters = _uiState.value.monsterList
        if (newIndex in monsters.indices && monsters.isNotEmpty()) {
            _uiState.update { currentState ->
                currentState.copy(
                    currentMonster = monsters[newIndex],
                    currentMonsterIndex = newIndex,
                    errorMonster = null
                )
            }
        }
    }

    fun monsterTookDamage() {
        val currentMonster = _uiState.value.currentMonster
        if (currentMonster != null) {
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
                    if (_uiState.value.currentMonster?.isAlive() == false) {
                        monsterDied()
                    }
                }
            }
        }
    }

    private fun monsterDied() {
        val currentMonster = _uiState.value.currentMonster
        if (currentMonster != null) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(
                        monsterDead = true,
                    )
                }
                delay(200)
                _uiState.update { currentState ->
                    currentState.copy(
                        monsterDead = false,
                        currentMonster = currentState.currentMonster?.die(),
                        currentMonsterIndex = currentState.currentMonsterIndex
                    )
                }
                updateMonsterSQLite()
            }
        }
    }

    private fun updateMonsterSQLite() {
        viewModelScope.launch {
            _uiState.value.currentMonster?.let {
                monsterRepository.updateMonster(
                    it.name,
                    it.deathCount
                )
            }
        }
    }

    fun insertAllMonsters(allMonsters: List<Monster> = listMonsterEntity) {
        viewModelScope.launch {
            val monsterList = monsterRepository.getMonsters().firstOrNull()
            if (monsterList.isNullOrEmpty()) {
                monsterRepository.insertAll(allMonsters)
            }
        }
    }
}

data class MonsterUiStage(
    val isLoading: Boolean = true,
    val monsterList: List<Monster> = emptyList(),
    val currentMonster: Monster? = null,
    val currentMonsterIndex: Int? = null,
    val tookDamage: Boolean = false,
    val monsterDead: Boolean = false,
    val errorMonster: String? = null
)

