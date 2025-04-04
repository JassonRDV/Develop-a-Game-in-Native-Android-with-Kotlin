package com.example.beatfranticallyidle.data.local.reward

import com.example.beatfranticallyidle.data.local.reward.model.Reward
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface RewardRepository {
    suspend fun insertReward(reward: Reward)
    suspend fun updateReward(reward: Reward)
    fun getRewards(): Flow<Reward>
    suspend fun updatePurchaseCost()
    suspend fun updateGoldBut()
    suspend fun updateTotalDeath()
    suspend fun updateGoldMonsterDeath(purchaseCost: BigDecimal)
}