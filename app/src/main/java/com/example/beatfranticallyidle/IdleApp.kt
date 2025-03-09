package com.example.beatfranticallyidle

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.beatfranticallyidle.ui.components.mainscreen.BottomBar
import com.example.beatfranticallyidle.ui.components.mainscreen.TopBar
import com.example.beatfranticallyidle.ui.screen.MainScreen
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.CardUiState
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

sealed class HeroCardRoute(val route: String) {
    object FireHero : HeroCardRoute("FireHero")
    object LightningHero : HeroCardRoute("LightningHero")
    object PoisonHero : HeroCardRoute("PoisonHero")
}

@Composable
fun AppIdle(
    monsterViewModel: MonsterViewModel = viewModel(),
    cardViewModel: CardViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val idleUiState by monsterViewModel.uiState.collectAsState()
    val cardUiState by cardViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    backgroundColor = Color(0x80000000),
                    modifier = Modifier
                )
            },
            bottomBar = {
                BottomBar(
                    cardViewModel = cardViewModel,
                    backgroundColor = Color(0x80000000),
                    navController = navController,
                    modifier = Modifier
                )
            },
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            modifier = Modifier
        ) { paddingValues ->
            MainScreen(
                cardViewModel = cardViewModel,
                cardUiState = cardUiState,
                monsterViewModel = monsterViewModel,
                idleUiState = idleUiState,
                navController = navController,
                paddingValues = paddingValues,
                modifier = Modifier,
            )
        }
        AnimatedVisibility(
            visible = false,
            modifier = Modifier.fillMaxSize()
        ) {
            HeroCardFullScreen(
                cardUiState = cardUiState,
                cardViewModel = cardViewModel,
                modifier = Modifier.padding(48.dp)
            )
        }
    }
}

@Composable
fun HeroCardFullScreen(
    modifier: Modifier,
    cardUiState: CardUiState,
    cardViewModel: CardViewModel
) {
    Box(
        modifier = modifier
            .clickable(
                role = Role.Image,
                onClick = {
                    cardViewModel.hideCardFullScreen()
                },
            )
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(cardUiState.currentCard.imageResId),
            contentDescription = null,
            modifier = Modifier
                .border(2.dp, Color.White)
        )
        Box(
            modifier = Modifier
                .padding(
                    bottom = 150.dp,
                    start = 12.dp,
                    end = 12.dp,
                    top = 150.dp
                )
                .fillMaxSize(),
        ) {
            Text(
                text = cardUiState.currentCard.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .align(alignment = Alignment.TopCenter)
                    .background(Color(0x90000000))
                    .wrapContentSize()
            )
            Text(
                text = cardUiState.currentCard.effectDescription,
                color = Color.White,
                fontSize = 28.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .background(Color(0x90000000))
                    .wrapContentSize()
            )
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