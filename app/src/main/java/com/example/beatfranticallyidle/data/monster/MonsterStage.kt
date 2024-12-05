package com.example.beatfranticallyidle.data.monster

import androidx.annotation.DrawableRes

data class MonsterStage(
    val currentMonster: MonsterInfo.Monster = monsterList[0],
    val lifeMonster: Float = currentMonster.life,
    val maxLifeMonster: Float = currentMonster.life,
    @DrawableRes val monsterImage: Int = currentMonster.image,
    val rewardType: RewardType = currentMonster.rewardType,
    val rewardValue: Int = 0,
    val tookDamage: Boolean = false,
    val monsterDead: Boolean = false,
    val allMonsterDeadCount: Int = 0,
)
