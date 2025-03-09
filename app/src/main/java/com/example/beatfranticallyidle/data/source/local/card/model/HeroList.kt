package com.example.beatfranticallyidle.data.source.local.card.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme

val listFireHero: List<Card> = listOf(
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = 0,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Inflige 1 de dano de fogo por segundo",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = 0,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Inflige 1 de dano de fogo por segundo",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = 0,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Inflige 1 de dano de fogo por segundo",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = 0,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Inflige 1 de dano de fogo por segundo",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = 0,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Inflige 1 de dano de fogo por segundo",
        imageTypeResId = R.drawable.card_null_fogo
    ),
    Card(
        name = "Espírito de Fogo",
        effect = CardEffect.DAMAGE_BOOST,
        discovered = false,
        effectActivated = false,
        numberCardCount = 0,
        imageResId = R.drawable.card_fogo_espirito,
        cardElementId = 1,
        effectDescription = "Inflige 1 de dano de fogo por segundo",
        imageTypeResId = R.drawable.card_null_fogo
    )
)

val listTypeHero: List<CardType> = listOf(
    CardType(cardElement = CardElement.FIRE),
    CardType(cardElement = CardElement.POISON),
    CardType(cardElement = CardElement.LIGHTNING),
    CardType(cardElement = CardElement.LIGHT)
)

val genericHero = Card(
    name = "Heroi generico",
    effect = CardEffect.DAMAGE_BOOST,
    discovered = false,
    effectActivated = false,
    numberCardCount = 0,
    imageResId = R.drawable.card_null,
    cardElementId = CardElement.FIRE.value,
    effectDescription = "apenas para testes",
    imageTypeResId = R.drawable.card_null
)

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle()
    }
}