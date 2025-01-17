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
    val maxLife: Float =0f,
    var currentLife: Float =0f,
    val rewardType: RewardType = RewardType.NULL,
    val baseRewardValue: Int = 0,
    var currentRewardValue: Int = 0,
    var deathCount: Int = 0,
)


val listMonsterEntity = mutableListOf(
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