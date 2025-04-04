package com.example.beatfranticallyidle.game_screen.main_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.local.monster.MonsterRepository
import com.example.beatfranticallyidle.data.local.reward.RewardRepository
import com.example.beatfranticallyidle.data.local.monster.model.Monster
import com.example.beatfranticallyidle.data.local.monster.model.listMonsterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.collections.isNotEmpty
import kotlin.collections.lastIndex

@HiltViewModel
class MonsterViewModel @Inject constructor(
    private val monsterRepository: MonsterRepository,
    private val rewardRepository: RewardRepository
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
                monsterRepository.getMonsters().collectLatest { monster ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            monsterList = monster,
                            currentMonster = monster[currentState.currentMonsterIndex ?: 0],
                            currentMonsterIndex = currentState.currentMonsterIndex ?: 0,
                            isLoading = false,
                            errorMonster = null
                        )
                    }
                }
            } catch (e: Exception) {
                insertAllMonsters()
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
                            currentMonster = currentMonster.takeDamage(BigDecimal(1))
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
                updateRewardSQLite()
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

    private fun updateRewardSQLite() {
        viewModelScope.launch {
            _uiState.value.currentMonster?.let {
                rewardRepository.updateTotalDeath()
                rewardRepository.updateGoldMonsterDeath(it.rewardValue)
            }
        }
    }

    private fun insertAllMonsters(allMonsters: List<Monster> = listMonsterEntity) {
        viewModelScope.launch {
            val monsterList = monsterRepository.getMonsters().firstOrNull()
            if (monsterList.isNullOrEmpty()) {
                monsterRepository.insertAll(allMonsters)
                loadMonster()
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

