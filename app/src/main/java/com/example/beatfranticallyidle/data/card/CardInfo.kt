package com.example.beatfranticallyidle.data.card

import androidx.annotation.DrawableRes

enum class TypeDamage { FIRE, LIGHTNING, POISON, NONE }

sealed class CardInfo() {
    data class Card(
        val name: String,
        val attackDamage: Int,
        val typeDamage: TypeDamage,
        val effect: String,
        @DrawableRes val image: Int
    )
}

