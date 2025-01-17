package com.example.beatfranticallyidle.data.source.local.OldMonsterData

import androidx.annotation.DrawableRes

enum class RewardType { GOLD, SOUL, NULL }

sealed class MonsterInfo {
    data class Monster(
        @DrawableRes val image: Int,
        @DrawableRes val icon: Int,
        @DrawableRes val imageArena: Int,
        val name: String,
        var currentLife: Float,
        val maxLife: Float,
        val rewardType: RewardType,
        val rewardValue: Int,
        var numberOfDeaths: Int
    )
}