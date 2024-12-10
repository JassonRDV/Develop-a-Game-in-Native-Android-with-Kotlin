package com.example.beatfranticallyidle.data.card

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme

val listFireHero: List<HeroInfo.Hero> = mutableListOf(
    HeroInfo.Hero(
        name = "Espírito de Fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "Inflige 1 de dano de fogo por segundo",
        image = R.drawable.espiritofogo
    ),
    HeroInfo.Hero(
        name = "Doquinho de Fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        image = R.drawable.card_doquinho_fogo
    ),
    HeroInfo.Hero(
        name = "Arqueira Incendiária",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        image = R.drawable.card_arqueira_incendiaria
    ),
    HeroInfo.Hero(
        name = "Mago de Fogo",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        image = R.drawable.magofogo
    ),
    HeroInfo.Hero(
        name = "Cavaleiro Solar",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        image = R.drawable.card_cavaleiro_solar
    ),
    HeroInfo.Hero(
        name = "Montador de Dragões",
        attackDamage = 1,
        typeDamage = TypeDamage.FIRE,
        effect = "decidindo o efeito",
        image = R.drawable.card_montador_dragao
    ),
)

val listToxicHero: List<HeroInfo.Hero> = mutableListOf(
    HeroInfo.Hero(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        image = R.drawable.card_null
    ),
    HeroInfo.Hero(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        image = R.drawable.card_null
    ),
    HeroInfo.Hero(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        image = R.drawable.card_null
    ),
    HeroInfo.Hero(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        image = R.drawable.card_null
    ),
    HeroInfo.Hero(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        image = R.drawable.card_null
    ),
    HeroInfo.Hero(
        name = "Quem será?",
        attackDamage = 1,
        typeDamage = TypeDamage.NONE,
        effect = "Qual será o seu efeito?",
        image = R.drawable.card_null
    ),
)

val listAllHeroes: List<List<HeroInfo.Hero>> = listOf( listFireHero, listToxicHero)

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle(modifier = Modifier.fillMaxSize())
    }
}