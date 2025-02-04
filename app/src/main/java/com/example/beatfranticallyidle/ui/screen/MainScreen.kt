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
import com.example.beatfranticallyidle.viewmodel.IdleStage
import com.example.beatfranticallyidle.viewmodel.IdleViewModel

@Composable
fun MainScreen(
    idleViewModel: IdleViewModel,
    idleUiState: IdleStage,
    navController: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        MonsterZone(
            idleViewModel = idleViewModel,
            idleUiState = idleUiState,
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
                    idleViewModel = idleViewModel,
                    idleUiState = idleUiState,
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
                    typeHero = 1,
                    idleViewModel = idleViewModel,
                    idleUiState = idleUiState,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                )
            }
            composable(route = HeroCardRoute.LightningHero.route) {
                HeroesZone(
                    background = R.drawable.background_black_hole,
                    paddingValues = paddingValues,
                    typeHero = 2,
                    idleViewModel = idleViewModel,
                    idleUiState = idleUiState,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
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