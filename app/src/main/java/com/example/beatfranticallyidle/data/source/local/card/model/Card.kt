package com.example.beatfranticallyidle.data.source.local.card.model

import androidx.annotation.DrawableRes
import com.example.beatfranticallyidle.data.source.local.card.CardEntity
import com.example.beatfranticallyidle.data.source.local.card.CardTypeEntity
import com.example.beatfranticallyidle.data.source.local.card.CardWithCardTypeEntity

enum class CardElement(val value: Int) {
    FIRE(0),
    POISON(1),
    LIGHTNING(2),
    LIGHT(3);

    companion object {
        fun fromInt(value: Int) = entries.first {it.value == value}
    }
}

enum class CardEffect {
    DAMAGE_BOOST, // Aumenta o dano
    GOLD_BOOST, // Aumenta o ouro ganho
}

data class CardType(
    val id: Int = 0,
    val cardElement: CardElement
) {
    fun toCardTypeEntity(): CardTypeEntity {
        return CardTypeEntity(
            id = id,
            cardElement = cardElement
        )
    }
}

data class Card(
    @DrawableRes val imageResId: Int,
    @DrawableRes val imageTypeResId: Int,
    val id: Int = 0,
    val name: String,
    val cardElementId: Int, // id do elemento da carta
    val effect: CardEffect, // tipo de efeito da carta
    val effectDescription: String, // descrição do efeito
    val discovered: Boolean, // carta descoberta ou não
    val effectActivated: Boolean, // efeito ativado ou não
    val numberCardCount: Int, // cartas repetidas
) {
    fun applyEffect() {

    }

    fun discover() : Card {
        return copy(discovered = true)
    }

    fun activateEffect() : Card {
        return copy(effectActivated = true)
    }

    fun numberCardCount() : Card {
        val newCount = numberCardCount + 1
        return copy(numberCardCount = newCount)
    }

    fun toCardEntity(): CardEntity {
        return CardEntity(
            id = id,
            imageResId = imageResId,
            imageTypeResId = imageTypeResId,
            name = name,
            effect = effect,
            effectDescription = effectDescription,
            discovered = discovered,
            effectActivated = effectActivated,
            numberCardCount = numberCardCount,
            cardElementId = cardElementId,
        )
    }
}

data class CardWithCardType(
    val cardType: CardType,
    val cards: List<Card>
) {
    fun toCardWithCardTypeEntity(): CardWithCardTypeEntity {
        return CardWithCardTypeEntity(
            cardType = cardType.toCardTypeEntity(),
            cards = cards.map { it.toCardEntity() }
        )
    }
}