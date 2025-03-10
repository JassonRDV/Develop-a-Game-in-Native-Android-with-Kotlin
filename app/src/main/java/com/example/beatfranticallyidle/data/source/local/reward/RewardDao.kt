package com.example.beatfranticallyidle.data.source.local.reward

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

    @Query("SELECT * FROM reward_table")
    fun getRewards(): Flow<RewardEntity>
}