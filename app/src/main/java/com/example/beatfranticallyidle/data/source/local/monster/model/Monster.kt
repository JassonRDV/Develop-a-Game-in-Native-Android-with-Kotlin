package com.example.beatfranticallyidle.data.source.local.monster.model

import androidx.annotation.DrawableRes
import com.example.beatfranticallyidle.R

enum class RewardType { GOLD, SOUL, NULL }

data class Monster(
    val id: Int = 0,
    @DrawableRes val imageResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val arenaResId: Int,
    val name: String,
    val description: String,
    val maxLife: Float,
    val currentLife: Float,
    val rewardType: RewardType,
    val rewardValue: Float,
    val deathCount: Int = 0,
) {

    fun takeDamage(damage: Float): Monster {
        var newCurrentLife = currentLife - damage
        if (newCurrentLife < 0) {
            newCurrentLife = 0f
        }
        return copy(currentLife = newCurrentLife)
    }

    fun isAlive(): Boolean {
        return currentLife > 0
    }

    private fun resetLife(): Float {
        val life = maxLife
        return life
    }

    /**
     * Após cada morte do monstro aumento a sua vida maxima em 20%,
     * aumentando assim a dificuldade
     **/
    private fun increaseMaxLife(): Float {
        val newMaxLife = maxLife * 1.2f
        return newMaxLife
    }

    private fun increaseRewardValue(increase: Float): Float {
        val valor = rewardValue * increase
        return valor
    }

    private fun increaseDeathCount(): Int {
        val newDeathCount = deathCount + 1
        return newDeathCount
    }

    fun die(): Monster {
        val newDeathCount = increaseDeathCount()
        val newMaxLife = increaseMaxLife()
        val newRewardValue = increaseRewardValue(1.1f)
        val newCurrentLife = resetLife()
        return copy(
            deathCount = newDeathCount,
            currentLife = newCurrentLife,
            maxLife = newMaxLife,
            rewardValue = newRewardValue
        )
    }
}

val GenericMonster =
    Monster(
        imageResId = R.drawable.monster_placeholder,
        iconResId = R.drawable.monster_icone_placeholder,
        arenaResId = R.drawable.background_placeholder,
        name = "Monster",
        maxLife = 1f,
        currentLife = 1f,
        rewardType = RewardType.NULL,
        rewardValue = 1f,
        deathCount = 0,
        description = ""
    )
