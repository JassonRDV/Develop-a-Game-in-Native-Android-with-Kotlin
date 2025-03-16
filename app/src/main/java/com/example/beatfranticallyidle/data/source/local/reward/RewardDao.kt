package com.example.beatfranticallyidle.data.source.local.reward

import android.icu.math.BigDecimal
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReward(reward: RewardEntity)

    @Update
    suspend fun updateReward(reward: RewardEntity)

    @Query("UPDATE reward_table SET purchaseCost = purchaseCost * 1.2 WHERE id = 1")
    suspend fun updatePurchaseCost()

    @Query("UPDATE reward_table SET gold = gold - purchaseCost WHERE id = 1")
    suspend fun updateGoldBut()

    @Query("UPDATE reward_table SET gold = gold + :purchaseCost WHERE id = 1")
    suspend fun updateGoldMonsterDeath(purchaseCost: BigDecimal)

    @Query("UPDATE reward_table SET totalDeath = totalDeath + 1 WHERE id = 1")
    suspend fun updateTotalDeath()

    @Query("SELECT * FROM reward_table")
    fun getRewards(): Flow<RewardEntity>
}