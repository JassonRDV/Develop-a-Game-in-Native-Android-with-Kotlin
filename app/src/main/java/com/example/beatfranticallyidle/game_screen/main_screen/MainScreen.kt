package com.example.beatfranticallyidle.game_screen.main_screen

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
import com.example.beatfranticallyidle.game_screen.main_screen.zone.HeroesZone
import com.example.beatfranticallyidle.game_screen.main_screen.zone.MonsterZone
import com.example.beatfranticallyidle.game_screen.main_screen.viewmodel.CardUiState
import com.example.beatfranticallyidle.game_screen.main_screen.viewmodel.CardViewModel
import com.example.beatfranticallyidle.game_screen.main_screen.viewmodel.MonsterUiStage
import com.example.beatfranticallyidle.game_screen.main_screen.viewmodel.MonsterViewModel
import com.example.beatfranticallyidle.game_screen.main_screen.viewmodel.RewardUiState
import com.example.beatfranticallyidle.game_sfx.SoundsViewModel

@Composable
fun MainScreen(
    monsterViewModel: MonsterViewModel,
    monsterUiState: MonsterUiStage,
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    cardUiState: CardUiState,
    cardViewModel: CardViewModel,
    rewardUiState: RewardUiState,
    soundsViewModel: SoundsViewModel,
) {
    Column(
        modifier = modifier
    ) {
        MonsterZone(
            rewardUiState = rewardUiState,
            monsterViewModel = monsterViewModel,
            monsterUiStage = monsterUiState,
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
                    background = R.drawable.background_sol_pondo,
                    soundsViewModel = soundsViewModel,
                    cardViewModel = cardViewModel,
                    currentCard = cardUiState,
                    paddingValues = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
            composable(route = HeroCardRoute.PoisonHero.route) {
                HeroesZone(
                    background = R.drawable.background_toxic,
                    paddingValues = paddingValues,
                    cardViewModel = cardViewModel,
                    currentCard = cardUiState,
                    soundsViewModel = soundsViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
            composable(route = HeroCardRoute.LightningHero.route) {
                HeroesZone(
                    background = R.drawable.background_black_hole,
                    paddingValues = paddingValues,
                    cardViewModel = cardViewModel,
                    currentCard = cardUiState,
                    soundsViewModel = soundsViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
        }
    }
}