package com.example.beatfranticallyidle.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.HeroCardRoute
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.ui.components.HeroesZone
import com.example.beatfranticallyidle.ui.components.MonsterZone
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.CardUiState
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterUiStage
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

@Composable
fun MainScreen(
    monsterViewModel: MonsterViewModel,
    idleUiState: MonsterUiStage,
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    cardUiState: CardUiState,
    cardViewModel: CardViewModel,
) {
    Column(
        modifier = modifier
    ) {
        MonsterZone(
            monsterViewModel = monsterViewModel,
            monsterUiStage = idleUiState,
            cardUiState = cardUiState,
            cardViewModel = cardViewModel,
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
//                HeroesZone(
//                    cardViewModel = cardViewModel,
//                    typeHero = 0,
//                    background = R.drawable.background_sol_pondo,
//                    paddingValues = paddingValues,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .weight(1f),
//                    cardUiStage = cardUiState,
//                )
            }
            composable(route = HeroCardRoute.PoisonHero.route) {
                HeroesZone(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    background = R.drawable.background_toxic,
                    paddingValues = paddingValues,
                    typeHero = 1,
                    cardViewModel = cardViewModel,
                    cardUiStage = cardUiState,
                )
            }
            composable(route = HeroCardRoute.LightningHero.route) {
                HeroesZone(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    background = R.drawable.background_black_hole,
                    paddingValues = paddingValues,
                    typeHero = 2,
                    cardViewModel = cardViewModel,
                    cardUiStage = cardUiState,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle()
    }
}