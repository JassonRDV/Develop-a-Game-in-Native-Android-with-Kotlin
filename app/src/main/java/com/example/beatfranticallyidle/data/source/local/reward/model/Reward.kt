package com.example.beatfranticallyidle.data.source.local.reward.model

import android.icu.math.BigDecimal
import com.example.beatfranticallyidle.data.source.local.reward.RewardEntity
import java.math.BigInteger

data class Reward(
    val id: Int = 0,
    val gold: BigDecimal,
    val totalDeath: BigInteger,
    val purchaseCost: BigDecimal
) {
    fun toRewardEntity(): RewardEntity {
        return RewardEntity(
            id = id,
            gold = gold,
            totalDeath = totalDeath,
            purchaseCost = purchaseCost
        )
    }
}

val reward = Reward(
    gold = BigDecimal.ZERO,
    totalDeath = BigInteger.ZERO,
    purchaseCost = BigDecimal(25)
)
