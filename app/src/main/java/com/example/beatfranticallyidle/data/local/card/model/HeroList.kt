package com.example.beatfranticallyidle.data.local.card.model

import com.example.beatfranticallyidle.R
import java.math.BigInteger

val listGenericTypeHero: List<CardType> = listOf(
    CardType(cardElement = CardElement.FIRE),
    CardType(cardElement = CardElement.POISON),
    CardType(cardElement = CardElement.LIGHTNING),
    CardType(cardElement = CardElement.LIGHT)
)

val listFireHero: List<Card> = listOf(
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = BigInteger.ZERO,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Gera automaticamente dano de fogo passivo ao longo do tempo.",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Cão de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = BigInteger.ZERO,
        imageResId = R.drawable.card_fogo_dog,
        cardElementId = 1,
        effectDescription = "Aumenta a velocidade de geração de dano de fogo passivo.",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Arqueira Incendiária",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = BigInteger.ZERO,
        imageResId = R.drawable.card_fogo_arqueira_incendiaria,
        cardElementId = 1,
        effectDescription = "Fornece um bônus de dano de fogo periódico baseado em tempo.",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Mago de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = BigInteger.ZERO,
        imageResId = R.drawable.card_fogo_mago,
        cardElementId = 1,
        effectDescription = "Multiplica a quantidade de dano de fogo passivo gerado.",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Cavaleiro Solar",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = BigInteger.ZERO,
        imageResId = R.drawable.card_fogo_cavaleiro_solar,
        cardElementId = 1,
        effectDescription = "Adiciona uma chance de dano de fogo crítico passivo.",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Montador de Dragão",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = BigInteger.ZERO,
        imageResId = R.drawable.card_fogo_montador_dragao,
        cardElementId = 1,
        effectDescription = "Gera um grande pulso de dano de fogo passivo a cada intervalo.",
        imageTypeResId = R.drawable.card_null_fogo
    ),
)

// vou usar para quando o usuário não tiver nenhum tipo de carta
val genericHero = Card(
    name = "Heroi generico",
    effect = CardEffect.DAMAGE_BOOST,
    discovered = false,
    effectActivated = false,
    numberCardCount = BigInteger.ZERO,
    imageResId = R.drawable.card_null,
    cardElementId = CardElement.FIRE.value,
    effectDescription = "apenas para testes",
    imageTypeResId = R.drawable.card_null
)