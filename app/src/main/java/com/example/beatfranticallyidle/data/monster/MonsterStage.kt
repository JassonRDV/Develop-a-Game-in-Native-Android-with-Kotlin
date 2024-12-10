package com.example.beatfranticallyidle.data.monster

import androidx.annotation.DrawableRes

data class MonsterStage(
    var currentMonster: MonsterInfo.Monster = monsterList[0],
    val lifeMonster: Float = currentMonster.currentLife,
    val maxLifeMonster: Float = currentMonster.maxLife,
    @DrawableRes val monsterImage: Int = currentMonster.image,
    val rewardType: RewardType = currentMonster.rewardType,
    val rewardValue: Int = 0,
    val tookDamage: Boolean = false,
    val monsterDead: Boolean = false,
    val allMonsterDeadCount: Int = 0,
    val monsterDeadCount: Int = currentMonster.numberOfDeaths
)
