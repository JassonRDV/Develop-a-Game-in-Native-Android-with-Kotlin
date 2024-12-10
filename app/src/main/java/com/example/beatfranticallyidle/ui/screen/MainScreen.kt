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
import com.example.beatfranticallyidle.data.monster.MonsterStage
import com.example.beatfranticallyidle.data.card.CardInfo
import com.example.beatfranticallyidle.data.card.CartState
import com.example.beatfranticallyidle.ui.components.HeroesZone
import com.example.beatfranticallyidle.ui.components.MonsterZone
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    listHeroes: List<List<CardInfo.Card>>,
    monsterViewModel: MonsterViewModel,
    monsterUiState: MonsterStage,
    navController: NavHostController,
    paddingValues: PaddingValues,
    cardUiStage: CartState,
    cardViewModel: CardViewModel
) {
    Column(
        modifier = modifier
    ) {
        MonsterZone(
            viewModel = monsterViewModel,
            uiState = monsterUiState,
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
                    cardUiStage = cardUiStage.currentCard[0],
                    cardViewModel = cardViewModel,
                    listHeroes = listHeroes[0],
                    background = R.drawable.background_sol_pondo,
                    paddingValues = paddingValues,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
            }
            composable(route = HeroCardRoute.PoisonHero.route) {
                HeroesZone(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    listHeroes = listHeroes[1],
                    background = R.drawable.black_hole,
                    paddingValues = paddingValues,
                    cardUiStage = cardUiStage.currentCard[1],
                    cardViewModel = cardViewModel,
                )
            }
            composable(route = HeroCardRoute.LightningHero.route) {
                HeroesZone(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    listHeroes = listHeroes[1],
                    background = R.drawable.black_hole,
                    paddingValues = paddingValues,
                    cardUiStage = cardUiStage.currentCard[1],
                    cardViewModel = cardViewModel,
                )
            }
        }
    }
}