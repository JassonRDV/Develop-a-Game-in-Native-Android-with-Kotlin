package com.example.beatfranticallyidle.data.source.local.OldMonsterData

import com.example.beatfranticallyidle.R

val monsterList = mutableListOf(
    MonsterInfo.Monster(
        image = R.drawable.monster_galo,
        icon = R.drawable.monster_galo_icone,
        imageArena = R.drawable.background_campo_aberto,
        currentLife = 10f,
        maxLife = 10f,
        rewardType = RewardType.GOLD,
        numberOfDeaths = 0,
        rewardValue = 5,
        name = "Galinha"
    ),
    MonsterInfo.Monster(
        image = R.drawable.monster_touro,
        icon = R.drawable.monster_touro_icone,
        currentLife = 30f,
        name = "Touro",
        maxLife = 30f,
        rewardType = RewardType.GOLD,
        rewardValue = 10,
        imageArena = R.drawable.background_arena,
        numberOfDeaths = 0
    )
)