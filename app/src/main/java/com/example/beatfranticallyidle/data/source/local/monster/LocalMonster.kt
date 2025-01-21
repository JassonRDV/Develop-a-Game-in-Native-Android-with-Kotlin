package com.example.beatfranticallyidle.data.source.local.monster

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.source.local.OldMonsterData.RewardType

@Entity(tableName = "monsters")
data class MonsterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val imageResId: Int = 0,
    @DrawableRes val iconResId: Int = 0,
    @DrawableRes val arenaResId: Int = 0,
    val name: String = "",
    val maxLife: Float = 1f,
    var currentLife: Float = 1f,
    val rewardType: RewardType = RewardType.NULL,
    val rewardValue: Int = 1,
    var deathCount: Int = 0,
)

val listMonsterEntity = mutableListOf(
    MonsterEntity(
        imageResId = R.drawable.monster_galo,
        iconResId = R.drawable.monster_galo_icone,
        arenaResId = R.drawable.background_campo_aberto,
        name = "Galinha",
        maxLife = 1f,
        rewardType = RewardType.GOLD,
        deathCount = 0,
        currentLife = 1f,
        rewardValue = 40
    ),
    MonsterEntity(
        imageResId = R.drawable.monster_touro,
        iconResId = R.drawable.monster_touro_icone,
        arenaResId = R.drawable.background_arena,
        name = "Touro",
        currentLife = 2f,
        maxLife = 2f,
        rewardType = RewardType.GOLD,
        rewardValue = 80,
        deathCount = 0
    )
)