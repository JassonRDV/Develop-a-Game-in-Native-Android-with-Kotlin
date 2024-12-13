package com.example.beatfranticallyidle.data.card

import androidx.annotation.DrawableRes

enum class TypeDamage { FIRE, LIGHTNING, POISON, NONE }

sealed class HeroInfo {
    data class Hero(
        @DrawableRes val imageHero: Int,
        @DrawableRes val imageNull: Int,
        val name: String,
        val attackDamage: Int,
        val typeDamage: TypeDamage,
        val effect: String,
        var discovered: Boolean,
        var effectActivated: Boolean,
        var numberCardCount: Int,
    )
}

