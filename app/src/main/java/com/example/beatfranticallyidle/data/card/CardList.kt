package com.example.beatfranticallyidle.data.card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme

private val listFireHeroes: List<CardInfo.Card> = listOf(
    CardInfo.Card(
        name = "Espírito de Fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "Inflige 1 de dano de fogo por segundo",
        price = 15,
        image = R.drawable.espiritofogo
    ),
    CardInfo.Card(
        name = "Doquinho de Fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        price = 30,
        image = R.drawable.card_doquinho_fogo
    ),
    CardInfo.Card(
        name = "Arqueira Incendiária",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        price = 45,
        image = R.drawable.card_arqueira_incendiaria
    ),
    CardInfo.Card(
        name = "Mago de Fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        price = 60,
        image = R.drawable.magofogo
    ),
    CardInfo.Card(
        name = "Cavaleiro Solar",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        price = 75,
        image = R.drawable.card_cavaleiro_solar
    ),
    CardInfo.Card(
        name = "Montador de Dragões",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        price = 90,
        image = R.drawable.card_montador_dragao
    ),
)

private val listNoneHeroes: List<CardInfo.Card> = listOf(
    CardInfo.Card(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        price = 0,
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        price = 0,
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        price = 0,
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        price = 0,
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        price = 0,
        image = R.drawable.card_null
    ),
    CardInfo.Card(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        price = 0,
        image = R.drawable.card_null
    ),
)

val listCard: List<List<CardInfo.Card>> = listOf(listFireHeroes, listNoneHeroes)

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle(modifier = Modifier.fillMaxSize())
    }
}