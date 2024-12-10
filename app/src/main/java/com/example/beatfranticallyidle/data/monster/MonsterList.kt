package com.example.beatfranticallyidle.data.monster

import com.example.beatfranticallyidle.R

val monsterList = mutableListOf(
    MonsterInfo.Monster(
        image = R.drawable.monstergalo,
        name = "Galinha",
        currentLife = 10f,
        maxLife = 10f,
        rewardType = RewardType.GOLD,
        rewardValue = 5,
        numberOfDeaths = 0
    ),
    MonsterInfo.Monster(
        image = R.drawable.monster_touro,
        name = "Touro",
        currentLife = 30f,
        maxLife = 30f,
        rewardType = RewardType.GOLD,
        rewardValue = 10,
        numberOfDeaths = 0
    )
)