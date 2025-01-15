package com.example.beatfranticallyidle.data.monster

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beatfranticallyidle.R

@Entity(tableName = "monsters")
data class MonsterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val imageResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val arenaResId: Int,
    val name: String,
    val maxLife: Float,
    var currentLife: Float,
    val rewardType: RewardType,
    val baseRewardValue: Int,
    var currentRewardValue: Int,
    var deathCount: Int = 0
)

val defaultMonsterEntity = mutableListOf(
    MonsterEntity(
        imageResId = R.drawable.monster_galo,
        iconResId = R.drawable.monster_galo_icone,
        arenaResId = R.drawable.background_campo_aberto,
        currentLife = 10f,
        maxLife = 10f,
        rewardType = RewardType.GOLD,
        deathCount = 0,
        baseRewardValue = 5,
        name = "Galinha",
        currentRewardValue = 5
    ),
    MonsterEntity(
        imageResId = R.drawable.monster_touro,
        iconResId = R.drawable.monster_touro_icone,
        currentLife = 30f,
        name = "Touro",
        maxLife = 30f,
        rewardType = RewardType.GOLD,
        baseRewardValue = 10,
        arenaResId = R.drawable.background_arena,
        deathCount = 0,
        currentRewardValue = 5
    )
)