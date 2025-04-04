package com.example.beatfranticallyidle.data.local.monster.model

import com.example.beatfranticallyidle.R
import java.math.BigDecimal
import java.math.BigInteger

val listMonsterEntity = mutableListOf(
    Monster(
        imageResId = R.drawable.monster_galo,
        iconResId = R.drawable.monster_galo_icone,
        arenaResId = R.drawable.background_campo_aberto,
        name = "Galinha",
        rewardType = RewardType.GOLD,
        maxLife = BigDecimal(40),
        deathCount = BigInteger.ZERO,
        currentLife = BigDecimal(40),
        rewardValue = BigDecimal(4000000000),
        description = ""
    ),
    Monster(
        imageResId = R.drawable.monster_touro,
        iconResId = R.drawable.monster_touro_icone,
        arenaResId = R.drawable.background_arena,
        name = "Touro",
        rewardType = RewardType.GOLD,
        maxLife = BigDecimal(80),
        deathCount = BigInteger.ZERO,
        currentLife = BigDecimal(80),
        rewardValue = BigDecimal(8000000000),
        description = ""
    )
)