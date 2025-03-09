package com.example.beatfranticallyidle.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.CardUiState
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.MonsterUiStage
import com.example.beatfranticallyidle.viewmodel.MonsterViewModel

@Composable
fun MonsterZone(
    monsterViewModel: MonsterViewModel,
    monsterUiStage: MonsterUiStage,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel,
    cardUiState: CardUiState,
) {
    Box(
        modifier = modifier
    ) {
        Background(
            idleUiState = monsterUiStage,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Monster(
                idleUiState = monsterUiStage,
                monsterViewModel = monsterViewModel,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(60.dp)
            )
            Box(
                contentAlignment = Alignment.BottomStart,
                modifier = Modifier
            ) {
                IconAndCount(
                    monsterUiStage = monsterUiStage.totalReward.toString(),
                    horArrangement = Arrangement.Start,
                    verAlignment = Alignment.Bottom,
                    iconImage = R.drawable.icone_coin,
                    fontSize = 24,
                    iconSize = 24,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                )
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                        .padding(8.dp)
                        .clickable { cardViewModel.buyCard() }
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "COMPRA",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.background(Color(0x50000000))
                        )
                        IconAndCount(
                            monsterUiStage = "ajeita depois",
                            horArrangement = Arrangement.Start,
                            verAlignment = Alignment.Top,
                            iconImage = R.drawable.icone_coin,
                            fontSize = 20,
                            iconSize = 20,
                            modifier = Modifier,
                        )
                    }
                }
            }
            IconAndCount(
                monsterUiStage = monsterUiStage.totalDeath.toString(),
                horArrangement = Arrangement.End,
                verAlignment = Alignment.Bottom,
                iconImage = R.drawable.icone_caveira,
                fontSize = 24,
                iconSize = 24,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
            )
            PreviousAndNextMonster(
                cardViewModel = cardViewModel,
                monsterViewModel = monsterViewModel,
                idleUiState = monsterUiStage,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(12.dp)
                    .background(
                        color = Color(0x50000000),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(4.dp)
            ) {
                Text(
                    text = monsterUiStage.currentMonster.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            LifeProgress(
                monsterUiState = monsterUiStage,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp)
            )
        }
    }
}

@Composable
private fun Background(
    modifier: Modifier = Modifier,
    idleUiState: MonsterUiStage,
) {
    Image(
        painter = painterResource(idleUiState.currentMonster.arenaResId),
        contentScale = ContentScale.FillHeight,
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
private fun LifeProgress(monsterUiState: MonsterUiStage, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
    ) {
        val progress = monsterUiState.currentMonster.currentLife /
                monsterUiState.currentMonster.maxLife
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.size(width = 250.dp, height = 24.dp),
            color = Color.Red,
            trackColor = Color.Transparent,
        )
        Text(
            text = "${monsterUiState.currentMonster.currentLife.toInt()}/" +
                    "${monsterUiState.currentMonster.maxLife.toInt()}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun Monster(
    idleUiState: MonsterUiStage,
    monsterViewModel: MonsterViewModel,
    modifier: Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        AnimatedVisibility(
            visible = !idleUiState.monsterDead,
            exit = scaleOut(),
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(idleUiState.currentMonster.imageResId),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                colorFilter = if (idleUiState.tookDamage) ColorFilter.tint(Color.White)
                else null,
                modifier = Modifier
                    .clickable(
                        role = Role.Image,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { monsterViewModel.monsterTookDamage() }
                    )
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            IconAndCount(
                monsterUiStage = idleUiState.currentMonster.rewardValue.toString(),
                horArrangement = Arrangement.Center,
                verAlignment = Alignment.Bottom,
                iconImage = R.drawable.icone_coin,
                fontSize = 20,
                iconSize = 24,
                modifier = Modifier
            )
            IconAndCount(
                monsterUiStage = idleUiState.currentMonster.deathCount.toString(),
                horArrangement = Arrangement.Center,
                verAlignment = Alignment.CenterVertically,
                iconImage = idleUiState.currentMonster.iconResId,
                fontSize = 20,
                iconSize = 24,
                modifier = Modifier
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
    monsterUiStage: String,
    iconSize: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = horArrangement,
        verticalAlignment = verAlignment,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0x50000000))
        ) {
            Image(
                painter = painterResource(iconImage),
                contentDescription = null,
                modifier = Modifier
                    .padding(2.dp)
                    .size(iconSize.dp)
            )
            Text(
                text = monsterUiStage,
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(2.dp)
            )
        }
    }
}

@Composable
private fun PreviousAndNextMonster(
    monsterViewModel: MonsterViewModel,
    modifier: Modifier = Modifier,
    idleUiState: MonsterUiStage,
    cardViewModel: CardViewModel,
) {
    Box(
        modifier = modifier
    ) {
        Button(
            onClick = {
                monsterViewModel.insertAllMonsters()
                cardViewModel.loadAllCards()
            },
            modifier = Modifier.align(alignment = Alignment.TopEnd)
        ) {
            Text(text = "Criar")
        }
        AnimatedVisibility(
            visible = (idleUiState.currentMonsterIndex != 0),
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(80.dp)
                    .clickable(
                        onClick = {
                            monsterViewModel.previousMonster()
                        }
                    )
            )
        }
        AnimatedVisibility(
            visible = (
                    idleUiState.currentMonster.deathCount > 0
                    ),
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier.align(alignment = Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(80.dp)
                    .clickable(
                        onClick = {
                            monsterViewModel.nextMonster()
                        }
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MonsterZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle()
    }
}