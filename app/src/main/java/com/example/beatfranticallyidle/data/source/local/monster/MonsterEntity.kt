package com.example.beatfranticallyidle.data.source.local.monster

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import com.example.beatfranticallyidle.data.source.local.monster.model.RewardType
import java.math.BigDecimal
import java.math.BigInteger

@Entity(tableName = "monsters")
data class MonsterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val imageResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val arenaResId: Int,
    val name: String,
    val description: String,
    val maxLife: BigDecimal,
    var currentLife: BigDecimal,
    val rewardType: RewardType,
    val rewardValue: BigDecimal,
    var deathCount: BigInteger = BigInteger.ZERO,
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

val listMonsterEntity = mutableListOf(
    Monster(
        imageResId = R.drawable.monster_galo,
        iconResId = R.drawable.monster_galo_icone,
        arenaResId = R.drawable.background_campo_aberto,
        name = "Galinha",
        rewardType = RewardType.GOLD,
        maxLife = BigDecimal(5),
        deathCount = BigInteger.ZERO,
        currentLife = BigDecimal(5),
        rewardValue = BigDecimal(40),
        description = ""
    ),
    Monster(
        imageResId = R.drawable.monster_touro,
        iconResId = R.drawable.monster_touro_icone,
        arenaResId = R.drawable.background_arena,
        name = "Touro",
        rewardType = RewardType.GOLD,
        currentLife = BigDecimal(10),
        maxLife = BigDecimal(10),
        rewardValue = BigDecimal(80),
        deathCount = BigInteger.ZERO,
        description = ""
    )
)