package com.example.beatfranticallyidle.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.monster.MonsterStage
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.HeroViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

@Composable
fun MonsterZone(
    viewModel: MonsterViewModel,
    uiState: MonsterStage,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    heroViewModel: HeroViewModel,
) {
    Box(
        modifier = modifier
    ) {
        Background(modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Life(
                uiState = uiState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp)
            )
            Monster(
                uiState = uiState,
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(60.dp)
            )
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                IconAndCount(
                    uiState = uiState.rewardValue.toString(),
                    horArrangement = Arrangement.Start,
                    verAlignment = Alignment.Bottom,
                    iconImage = R.drawable.icone_coin,
                    fontSize = 20,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                )
                Text(
                    text = "COMPRA",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .background(Color.Green)
                        .clickable { heroViewModel.compraCarta() }
                )
            }
            IconAndCount(
                uiState = uiState.allMonsterDeadCount.toString(),
                horArrangement = Arrangement.Start,
                verAlignment = Alignment.Top,
                iconImage = R.drawable.icone_caveira,
                fontSize = 20,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
            )
            PreviousAndNextMonster(
                viewModel = viewModel,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(
                text = uiState.currentMonster.name,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .padding(20.dp)
            )
        }
    }
}

@Composable
private fun Background(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.monsterarena),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
private fun Life(uiState: MonsterStage, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
    ) {
        LinearProgressIndicator(
            progress = { uiState.lifeMonster / uiState.maxLifeMonster },
            color = Color.Red,
            trackColor = Color.Transparent,
            modifier = Modifier
        )
    }
}

@Composable
private fun Monster(
    uiState: MonsterStage,
    viewModel: MonsterViewModel,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        IconAndCount(
            uiState = uiState.currentMonster.numberOfDeaths.toString(),
            horArrangement = Arrangement.End,
            verAlignment = Alignment.Bottom,
            iconImage = R.drawable.icone_caveira,
            fontSize = 20,
        )
        AnimatedVisibility(
            visible = !uiState.monsterDead,
            exit = scaleOut(),
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(uiState.monsterImage),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                colorFilter = if (uiState.tookDamage) ColorFilter.tint(Color.White)
                else null,
                modifier = Modifier
                    .clickable(
                        role = Role.Image,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { viewModel.monsterTookDamage() }
                    )
            )
        }
    }
}

@Composable
private fun IconAndCount(
    horArrangement: Arrangement.Horizontal,
    verAlignment: Alignment.Vertical,
    @DrawableRes iconImage: Int,
    fontSize: Int,
    uiState: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = horArrangement,
        verticalAlignment = verAlignment,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(iconImage),
            contentDescription = null,
            modifier = Modifier
        )
        Text(
            text = uiState,
            fontSize = fontSize.sp,
            color = Color.Red,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
private fun PreviousAndNextMonster(viewModel: MonsterViewModel, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .clickable(
                    onClick = { viewModel.previousMonster() }
                )
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowRight,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(80.dp)
                .clickable(
                    onClick = { viewModel.nextMonster() }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MonsterZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle(modifier = Modifier.fillMaxSize())
    }
}