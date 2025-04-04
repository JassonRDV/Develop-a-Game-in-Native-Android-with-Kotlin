package com.example.beatfranticallyidle.data.local.reward

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beatfranticallyidle.data.local.reward.model.Reward
import java.math.BigDecimal
import java.math.BigInteger

@Entity(tableName = "reward_table")
data class RewardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val gold: BigDecimal,
    val purchaseCost: BigDecimal,
    val totalDeath: BigInteger,
) {
    fun toReward(): Reward {
        return Reward(
            id = id,
            gold = gold,
            totalDeath = totalDeath,
            purchaseCost = purchaseCost
        )
    }
}
