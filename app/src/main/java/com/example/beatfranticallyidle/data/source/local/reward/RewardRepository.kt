package com.example.beatfranticallyidle.data.source.local.reward

import android.icu.math.BigDecimal
import com.example.beatfranticallyidle.data.source.local.reward.model.Reward
import kotlinx.coroutines.flow.Flow

interface RewardRepository {
    suspend fun insertReward(reward: Reward)
    suspend fun updateReward(reward: Reward)
    fun getRewards(): Flow<Reward>
    suspend fun updatePurchaseCost()
    suspend fun updateGoldBut()
    suspend fun updateTotalDeath()
    suspend fun updateGoldMonsterDeath(purchaseCost: BigDecimal)
}