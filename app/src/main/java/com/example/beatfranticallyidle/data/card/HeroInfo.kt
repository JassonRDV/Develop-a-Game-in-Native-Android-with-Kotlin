package com.example.beatfranticallyidle.data.card

import androidx.annotation.DrawableRes

enum class TypeDamage { FIRE, LIGHTNING, POISON, NONE }

sealed class HeroInfo {
    data class Hero(
        val name: String,
        val attackDamage: Int,
        val typeDamage: TypeDamage,
        val effect: String,
        var discovered: Boolean = false,
        @DrawableRes val image: Int
    )
}

