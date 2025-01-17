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
import com.example.beatfranticallyidle.viewmodel.IdleStage
import com.example.beatfranticallyidle.viewmodel.IdleViewModel

sealed class HeroCardRoute(val route: String) {
    object FireHero : HeroCardRoute("FireHero")
    object LightningHero : HeroCardRoute("LightningHero")
    object PoisonHero : HeroCardRoute("PoisonHero")
}

@Composable
fun AppIdle(
    idleViewModel: IdleViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val idleUiState by idleViewModel.uiState.collectAsState()
    val monsterUiState by idleViewModel.monsterUiState.collectAsState()
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
                    backgroundColor = Color(0x80000000),
                    navController = navController,
                    idleViewModel = idleViewModel,
                    modifier = Modifier
                )
            },
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            modifier = Modifier
        ) { paddingValues ->
            MainScreen(
                monsterUiState = monsterUiState,
                idleViewModel = idleViewModel,
                idleUiState = idleUiState,
                navController = navController,
                paddingValues = paddingValues,
                modifier = Modifier,
            )
        }
        AnimatedVisibility(
            visible = idleUiState.showHeroDetails,
            modifier = Modifier.fillMaxSize()
        ) {
            HeroCardFullScreen(
                idleViewModel = idleViewModel,
                monsterUiState = idleUiState,
                modifier = Modifier.padding(48.dp)
            )
        }
    }
}

@Composable
fun HeroCardFullScreen(
    modifier: Modifier,
    idleViewModel: IdleViewModel,
    monsterUiState: IdleStage
) {
    Box(
        modifier = modifier
            .clickable(
                role = Role.Image,
                onClick = {
                    idleViewModel.hideCardFullScreen()
                },
            )
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(monsterUiState.currentHero.imageHero),
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
                text = monsterUiState.currentHero.name,
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
                text = monsterUiState.currentHero.effect,
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