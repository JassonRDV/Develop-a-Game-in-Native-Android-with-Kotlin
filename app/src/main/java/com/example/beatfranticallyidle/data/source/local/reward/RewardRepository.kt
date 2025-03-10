package com.example.beatfranticallyidle.data.source.local.reward

import com.example.beatfranticallyidle.data.source.local.reward.model.Reward
import kotlinx.coroutines.flow.Flow

interface RewardRepository {

    suspend fun insertReward(reward: Reward)
    suspend fun updateReward(reward: Reward)
    fun getRewards(): Flow<Reward>
}