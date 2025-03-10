package com.example.beatfranticallyidle.data.source.local.reward

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beatfranticallyidle.data.source.local.reward.model.Reward

@Entity(tableName = "reward_table")
data class RewardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val gold: Float,
    val totalDeath: Int,
) {
    fun toReward(): Reward {
        return Reward(
            id = id,
            gold = gold,
            totalDeath = totalDeath
        )
    }
}
