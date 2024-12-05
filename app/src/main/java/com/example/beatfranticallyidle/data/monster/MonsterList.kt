package com.example.beatfranticallyidle.data.monster

import com.example.beatfranticallyidle.R

val monsterList = listOf(
    MonsterInfo.Monster(
        image = R.drawable.monstergalo,
        name = "Galinha",
        life = 15f,
        rewardType = RewardType.GOLD,
        rewardValue = 5,
        numberOfDeaths = 0
    ),
    MonsterInfo.Monster(
        image = R.drawable.monster_touro,
        name = "Touro",
        life = 30f,
        rewardType = RewardType.GOLD,
        rewardValue = 10,
        numberOfDeaths = 0
    )
)