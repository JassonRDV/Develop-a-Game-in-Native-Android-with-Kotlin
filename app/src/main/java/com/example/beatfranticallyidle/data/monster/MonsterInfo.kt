package com.example.beatfranticallyidle.data.monster

import androidx.annotation.DrawableRes

enum class RewardType { GOLD, SOUL }

sealed class MonsterInfo {
    data class Monster(
        @DrawableRes val image: Int,
        val name: String,
        val life: Float,
        val rewardType: RewardType,
        val rewardValue: Int,
        var numberOfDeaths: Int
    )
}