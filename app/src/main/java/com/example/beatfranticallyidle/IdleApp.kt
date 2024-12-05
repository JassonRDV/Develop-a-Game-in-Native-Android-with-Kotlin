package com.example.beatfranticallyidle

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.beatfranticallyidle.data.card.CardInfo
import com.example.beatfranticallyidle.data.card.listHero
import com.example.beatfranticallyidle.ui.components.mainscreen.BottomBar
import com.example.beatfranticallyidle.ui.components.mainscreen.TopBar
import com.example.beatfranticallyidle.ui.screen.MainScreen
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

sealed class HeroCardRoute(val route: String) {
    object FireHero : HeroCardRoute("FireHero")
    object LightningHero : HeroCardRoute("LightningHero")
    object PoisonHero : HeroCardRoute("PoisonHero")
}

@Composable
fun AppIdle(
    listHeroes: List<List<CardInfo.Card>> = listHero,
    viewModel: MonsterViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
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
                modifier = Modifier.height(92.dp)
            )
        },
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
        modifier = modifier
    ) { paddingValues ->
        MainScreen(
            listHeroes = listHeroes,
            viewModel = viewModel,
            uiState = uiState,
            navController = navController,
            modifier = Modifier,
            paddingValues = paddingValues
        )
    }
}
