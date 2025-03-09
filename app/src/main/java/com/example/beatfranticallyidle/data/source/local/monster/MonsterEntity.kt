package com.example.beatfranticallyidle.data.source.local.monster

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import com.example.beatfranticallyidle.data.source.local.monster.model.RewardType

@Entity(tableName = "monsters")
data class MonsterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val imageResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val arenaResId: Int,
    val name: String,
    val description: String,
    val maxLife: Float,
    var currentLife: Float,
    val rewardType: RewardType,
    val rewardValue: Float,
    var deathCount: Int = 0,
) {
    fun toMonster(): Monster {
        return Monster(
            id = id,
            imageResId = imageResId,
            iconResId = iconResId,
            arenaResId = arenaResId,
            name = name,
            description = description,
            maxLife = maxLife,
            currentLife = currentLife,
            rewardType = rewardType,
            rewardValue = rewardValue,
            deathCount = deathCount
        )
    }
}

fun Monster.toMonsterEntity() : MonsterEntity {
    return MonsterEntity(
        id = id,
        imageResId = imageResId,
        iconResId = iconResId,
        arenaResId = arenaResId,
        name = name,
        description = description,
        maxLife = maxLife,
        currentLife = currentLife,
        rewardType = rewardType,
        rewardValue = rewardValue,
        deathCount = deathCount,
    )
}

val listMonsterEntity = mutableListOf(
    Monster(
        imageResId = R.drawable.monster_galo,
        iconResId = R.drawable.monster_galo_icone,
        arenaResId = R.drawable.background_campo_aberto,
        name = "Galinha",
        maxLife = 5f,
        rewardType = RewardType.GOLD,
        deathCount = 0,
        currentLife = 5f,
        rewardValue = 40f,
        description = ""
    ),
    Monster(
        imageResId = R.drawable.monster_touro,
        iconResId = R.drawable.monster_touro_icone,
        arenaResId = R.drawable.background_arena,
        name = "Touro",
        currentLife = 10f,
        maxLife = 10f,
        rewardType = RewardType.GOLD,
        rewardValue = 80f,
        deathCount = 0,
        description = ""
    )
)