package com.example.beatfranticallyidle.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beatfranticallyidle.data.source.local.monster.MonsterRepository
import com.example.beatfranticallyidle.data.source.local.reward.RewardRepository
import com.example.beatfranticallyidle.data.source.local.reward.model.Reward
import com.example.beatfranticallyidle.data.source.local.reward.model.reward
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    private val rewardRepository: RewardRepository, private val monsterRepository: MonsterRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RewardUiState())
    val uiState: StateFlow<RewardUiState> = _uiState.asStateFlow()

    init {
        loadReward()
    }

    private fun loadReward() {
        viewModelScope.launch {
            try {
                rewardRepository.getRewards().collectLatest { result ->
                    _uiState.update { currentStage ->
                        currentStage.copy(
                            isLoading = false,
                            reward = result
                        )
                    }
                }
            } catch (e: Exception) {
                insertReward()
            }
        }
    }

    private fun insertReward(rewardi: Reward = reward) {
        viewModelScope.launch {
            val reward = _uiState.value.reward
            if (reward == null) {
                rewardRepository.insertReward(rewardi)
                loadReward()
            }
        }
    }
}

data class RewardUiState(
    val isLoading: Boolean = true,
    val reward: Reward? = null,
)