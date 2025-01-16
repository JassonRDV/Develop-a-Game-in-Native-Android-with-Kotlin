package com.example.beatfranticallyidle.data.source

import com.example.beatfranticallyidle.data.source.local.OldMonsterData.MonsterInfo
import com.example.beatfranticallyidle.data.source.local.OldMonsterData.monsterList
import com.example.beatfranticallyidle.data.source.local.card.HeroInfo
import com.example.beatfranticallyidle.data.source.local.card.listAllHeroes

data class IdleStage(
    // Informação do monstro atual
    var currentMonster: MonsterInfo.Monster = monsterList[0],

    // Recompensas e mortes
    var totalReward: Float = 0f,
    val tookDamage: Boolean = false,
    val monsterDead: Boolean = false,
    val numberAllDeath: Int = 0,

    // Lista de heróis e herói atual
    val allListHero: List<List<HeroInfo.Hero>> = listAllHeroes,
    val currentListHero: List<HeroInfo.Hero> = allListHero[0],
    val currentHero: HeroInfo.Hero = currentListHero[0],

    // Estado do herói atual
    val showHeroDetails: Boolean = false,
    var purchaseCost: Float = 10f,
    val increasedPurchaseCost: Float = 1.25f
)
