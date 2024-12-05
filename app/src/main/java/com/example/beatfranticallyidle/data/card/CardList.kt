package com.example.beatfranticallyidle.data.card

import com.example.beatfranticallyidle.R

private val listFireHeroes: List<CardInfo.Card> = listOf(
    CardInfo.Card(
        name = "Espirito de fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "",
        image = R.drawable.espiritofogo
    ),
    CardInfo.Card(
        name = "dog de fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "",
        image = R.drawable.dogfogo
    ),
    CardInfo.Card(
        name = "Espirito de fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "",
        image = R.drawable.argueirofogo
    ),
    CardInfo.Card(
        name = "Espirito de fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "",
        image = R.drawable.magofogo
    ),
    CardInfo.Card(
        name = "Espirito de fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "",
        image = R.drawable.guerreirofogo
    ),
    CardInfo.Card(
        name = "Espirito de fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "",
        image = R.drawable.dragaofogo
    ),
)

private val listNoneHeroes: List<CardInfo.Card> = listOf(
    CardInfo.Card(
        name = "",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "",
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "",
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "",
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "",
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "",
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "",
        image = R.drawable.card_null
    ),
)

val listHero: List<List<CardInfo.Card>> = listOf(listFireHeroes, listNoneHeroes)