package com.example.beatfranticallyidle

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.beatfranticallyidle.ui.components.mainscreen.BottomBar
import com.example.beatfranticallyidle.ui.components.mainscreen.TopBar
import com.example.beatfranticallyidle.ui.screen.MainScreen
import com.example.beatfranticallyidle.viewmodel.HeroViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

sealed class HeroCardRoute(val route: String) {
    object FireHero : HeroCardRoute("FireHero")
    object LightningHero : HeroCardRoute("LightningHero")
    object PoisonHero : HeroCardRoute("PoisonHero")
}

@Composable
fun AppIdle(
    heroViewModel: HeroViewModel = viewModel(),
    monsterViewModel: MonsterViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val monsterUiState by monsterViewModel.uiState.collectAsState()
    val heroUiState by heroViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopBar(
                backgroundColor = Color(0x50000000),
                modifier = Modifier
            )
        },
        bottomBar = {
            BottomBar(
                backgroundColor = Color(0x50000000),
                navController = navController,
                modifier = Modifier,
                heroViewModel = heroViewModel,
            )
        },
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
        modifier = modifier
    ) { paddingValues ->
        MainScreen(
            monsterViewModel = monsterViewModel,
            monsterUiState = monsterUiState,
            heroViewModel = heroViewModel,
            heroUiStage = heroUiState,
            navController = navController,
            paddingValues = paddingValues,
            modifier = Modifier
        )
    }
}
