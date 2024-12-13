package com.example.beatfranticallyidle.data.monster

import com.example.beatfranticallyidle.R

val monsterList = mutableListOf(
    MonsterInfo.Monster(
        image = R.drawable.monstergalo,
        icon = R.drawable.icone_mini_galo,
        name = "Galinha",
        currentLife = 10f,
        maxLife = 10f,
        rewardType = RewardType.GOLD,
        rewardValue = 5,
        imageArena = R.drawable.background_campo_aberto,
        numberOfDeaths = 0
    ),
    MonsterInfo.Monster(
        image = R.drawable.monster_touro,
        icon = R.drawable.icone_mini_touro,
        name = "Touro",
        currentLife = 30f,
        maxLife = 30f,
        rewardType = RewardType.GOLD,
        rewardValue = 10,
        imageArena = R.drawable.background_arena,
        numberOfDeaths = 0
    )
)