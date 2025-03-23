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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.beatfranticallyidle.ui.components.BottomBar
import com.example.beatfranticallyidle.ui.components.TopBar
import com.example.beatfranticallyidle.ui.screen.game.mainscreen.MainScreen
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.CardUiState
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel
import com.example.beatfranticallyidle.viewmodel.RewardViewModel
import com.example.beatfranticallyidle.viewmodel.SoundsViewModel

sealed class HeroCardRoute(val route: String) {
    data object FireHero : HeroCardRoute("FireHero")
    data object LightningHero : HeroCardRoute("LightningHero")
    data object PoisonHero : HeroCardRoute("PoisonHero")
}

@Composable
fun AppIdle(
    monsterViewModel: MonsterViewModel = viewModel(),
    cardViewModel: CardViewModel = viewModel(),
    rewardViewModel: RewardViewModel = viewModel(),
    soundsViewModel: SoundsViewModel = viewModel()
) {
    val monsterUiState by monsterViewModel.uiState.collectAsState()
    val cardUiState by cardViewModel.uiState.collectAsState()
    val rewardUiState by rewardViewModel.uiState.collectAsState()

    val clickSound = R.raw.click_01
    val clickSoundId = 1

    LaunchedEffect(key1 = true) {
        soundsViewModel.playBackgroundSound(R.raw.background_music_01)
        soundsViewModel.loadSoundEffect(clickSound, clickSoundId)
    }

    val navController = rememberNavController()
    Box(
        contentAlignment = Alignment.Center,
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
                rewardUiState = rewardUiState,
                cardViewModel = cardViewModel,
                cardUiState = cardUiState,
                monsterViewModel = monsterViewModel,
                monsterUiState = monsterUiState,
                navController = navController,
                paddingValues = paddingValues,
                soundsViewModel = soundsViewModel,
                modifier = Modifier,
            )
        }
        AnimatedVisibility(
            visible = cardUiState.showCardFullScreen,
            modifier = Modifier.fillMaxSize()
        ) {
            HeroCardFullScreen(
                soundsViewModel = soundsViewModel,
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
    cardViewModel: CardViewModel,
    soundsViewModel: SoundsViewModel
) {
    Box(
        modifier = modifier
            .clickable(
                role = Role.Image,
                onClick = {
                    cardViewModel.hideCardFullScreen()
                    soundsViewModel.playSoundEffect(1)
                },
            )
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        cardUiState.currentCard?.let { painterResource(it.imageResId) }?.let {
            Image(
                painter = it,
                contentDescription = null,
                modifier = Modifier
                    .border(2.dp, Color.White)
            )
        }
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
            cardUiState.currentCard?.name?.let {
                Text(
                    text = it,
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
            }
            cardUiState.currentCard?.let {
                Text(
                    text = it.effectDescription,
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
}

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle()
    }
}