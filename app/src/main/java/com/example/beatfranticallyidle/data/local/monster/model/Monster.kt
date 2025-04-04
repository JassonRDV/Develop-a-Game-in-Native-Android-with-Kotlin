package com.example.beatfranticallyidle.data.local.monster.model

import androidx.annotation.DrawableRes
import com.example.beatfranticallyidle.data.local.monster.MonsterEntity
import java.math.BigDecimal
import java.math.BigInteger

enum class RewardType { GOLD, SOUL, NULL }

data class Monster(
    val id: Int = 0,
    @DrawableRes val imageResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val arenaResId: Int,
    val name: String,
    val description: String,
    val maxLife: BigDecimal,
    val currentLife: BigDecimal,
    val rewardType: RewardType,
    val rewardValue: BigDecimal,
    val deathCount: BigInteger = BigInteger.ZERO,
) {
    fun takeDamage(damage: BigDecimal): Monster {
        var newCurrentLife = currentLife.subtract(damage)
        if (newCurrentLife < BigDecimal(0)) {
            newCurrentLife = BigDecimal(0)
        }
        return copy(currentLife = newCurrentLife)
    }

    fun isAlive(): Boolean {
        return currentLife > BigDecimal(0)
    }

    private fun resetLife(): BigDecimal {
        val life = maxLife
        return life
    }

    private fun increaseMaxLife(): BigDecimal {
        val newMaxLife = maxLife.multiply(BigDecimal(1.2))
        return newMaxLife
    }

    private fun increaseRewardValue(increase: BigDecimal): BigDecimal {

        val valor = rewardValue.multiply(increase)
        return valor
    }

    private fun increaseDeathCount(): BigInteger {
        val newDeathCount = deathCount.add(BigInteger.ONE)
        return newDeathCount
    }

    fun die(): Monster {
        val newMaxLife = increaseMaxLife()
        val newDeathCount = increaseDeathCount()
        val newCurrentLife = resetLife()
        return copy(
            maxLife = newMaxLife,
            deathCount = newDeathCount,
            currentLife = newCurrentLife,
        )
    }

    fun toMonsterEntity(): MonsterEntity {
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
}

