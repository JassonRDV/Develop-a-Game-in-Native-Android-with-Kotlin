package com.example.beatfranticallyidle.data.card

data class HeroState(
    val allListHero: List<List<HeroInfo.Hero>> = listAllHeroes,
    val currentListHero: List<HeroInfo.Hero> = allListHero[0],
    val currentHero: HeroInfo.Hero = currentListHero[0],
    val discovered: Boolean = currentHero.discovered,
    val showHeroDetails: Boolean = false,
)
