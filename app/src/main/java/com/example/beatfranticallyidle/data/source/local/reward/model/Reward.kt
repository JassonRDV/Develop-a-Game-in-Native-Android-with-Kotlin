package com.example.beatfranticallyidle.data.source.local.reward.model

import com.example.beatfranticallyidle.data.source.local.reward.RewardEntity

data class Reward(
    val id: Int,
    val gold: Float,
    val totalDeath: Int,
) {
    fun toRewardEntity(): RewardEntity {
        return RewardEntity(
            id = id,
            gold = gold,
            totalDeath = totalDeath
        )
    }
}

val reward = Reward(
    id = 1,
    gold = 0f,
    totalDeath = 0
)