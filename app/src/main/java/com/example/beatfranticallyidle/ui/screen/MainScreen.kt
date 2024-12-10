package com.example.beatfranticallyidle.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.beatfranticallyidle.HeroCardRoute
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.card.HeroState
import com.example.beatfranticallyidle.data.monster.MonsterStage
import com.example.beatfranticallyidle.ui.components.HeroesZone
import com.example.beatfranticallyidle.ui.components.MonsterZone
import com.example.beatfranticallyidle.viewmodel.HeroViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

@Composable
fun MainScreen(
    monsterViewModel: MonsterViewModel,
    monsterUiState: MonsterStage,
    heroViewModel: HeroViewModel,
    heroUiStage: HeroState,
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        MonsterZone(
            viewModel = monsterViewModel,
            uiState = monsterUiState,
            heroViewModel = heroViewModel,
            paddingValues = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        NavHost(
            navController = navController,
            startDestination = HeroCardRoute.FireHero.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(route = HeroCardRoute.FireHero.route) {
                HeroesZone(
                    typeHero = 0,
                    background = R.drawable.background_sol_pondo,
                    heroViewModel = heroViewModel,
                    heroUiStage = heroUiStage,
                    paddingValues = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
            composable(route = HeroCardRoute.PoisonHero.route) {
                HeroesZone(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    background = R.drawable.black_hole,
                    paddingValues = paddingValues,
                    heroViewModel = heroViewModel,
                    heroUiStage = heroUiStage,
                    typeHero = 1,
                )
            }
            composable(route = HeroCardRoute.LightningHero.route) {
                HeroesZone(
                    typeHero = 2,
                    background = R.drawable.black_hole,
                    paddingValues = paddingValues,
                    heroViewModel = heroViewModel,
                    heroUiStage = heroUiStage,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
        }
    }
}