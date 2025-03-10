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
import com.example.beatfranticallyidle.ui.components.HeroesZone
import com.example.beatfranticallyidle.ui.components.MonsterZone
import com.example.beatfranticallyidle.viewmodel.CardUiState
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterUiStage
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel
import com.example.beatfranticallyidle.viewmodel.RewardUiState
import com.example.beatfranticallyidle.viewmodel.RewardViewModel

@Composable
fun MainScreen(
    monsterViewModel: MonsterViewModel,
    idleUiState: MonsterUiStage,
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    cardUiState: CardUiState,
    cardViewModel: CardViewModel,
    rewardUiState: RewardUiState,
    rewardViewModel: RewardViewModel,
) {
    Column(
        modifier = modifier
    ) {
        MonsterZone(
            rewardViewModel = rewardViewModel,
            rewardUiState = rewardUiState,
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
                HeroesZone(
                    cardViewModel = cardViewModel,
                    currentCard = cardUiState,
                    background = R.drawable.background_sol_pondo,
                    paddingValues = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
            composable(route = HeroCardRoute.PoisonHero.route) {
                HeroesZone(
                    cardViewModel = cardViewModel,
                    currentCard = cardUiState,
                    background = R.drawable.background_toxic,
                    paddingValues = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
            composable(route = HeroCardRoute.LightningHero.route) {
                HeroesZone(
                    cardViewModel = cardViewModel,
                    currentCard = cardUiState,
                    background = R.drawable.background_black_hole,
                    paddingValues = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
        }
    }
}