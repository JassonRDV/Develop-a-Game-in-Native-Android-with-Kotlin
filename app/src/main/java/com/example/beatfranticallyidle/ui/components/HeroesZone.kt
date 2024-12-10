package com.example.beatfranticallyidle.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.data.card.HeroInfo
import com.example.beatfranticallyidle.data.card.HeroState
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.HeroViewModel

@Composable
fun HeroesZone(
    modifier: Modifier = Modifier,
    background: Int,
    paddingValues: PaddingValues,
    heroViewModel: HeroViewModel,
    heroUiStage: HeroState,
    typeHero: Int
) {
    val bottomPadding = paddingValues.calculateBottomPadding()
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painterResource(background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                UnitCard(
                    modifier = modifier,
                    heroViewModel = heroViewModel,
                    heroUiStage = heroUiStage.allListHero[typeHero][0],
                )
                UnitCard(
                    heroViewModel = heroViewModel,
                    modifier = modifier,
                    heroUiStage = heroUiStage.allListHero[typeHero][1],
                )
                UnitCard(
                    heroViewModel = heroViewModel,
                    modifier = modifier,
                    heroUiStage = heroUiStage.allListHero[typeHero][2],
                )
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                UnitCard(
                    heroViewModel = heroViewModel,
                    modifier = modifier,
                    heroUiStage = heroUiStage.allListHero[typeHero][3],
                )
                UnitCard(
                    heroViewModel = heroViewModel,
                    modifier = modifier,
                    heroUiStage = heroUiStage.allListHero[typeHero][4],
                )
                UnitCard(
                    heroViewModel = heroViewModel,
                    modifier = modifier,
                    heroUiStage = heroUiStage.allListHero[typeHero][5],
                )
            }
        }
    }
}

@Composable
fun UnitCard(
    heroViewModel: HeroViewModel,
    modifier: Modifier = Modifier,
    heroUiStage: HeroInfo.Hero,
) {
    if (heroUiStage.discovered) {
        HeroRevealed(
            heroViewModel = heroViewModel,
            heroUiStage = heroUiStage,
            modifier = modifier,
        )
    } else {
        HeroHidden(
            modifier = modifier
        )
    }
}

@Composable
private fun HeroRevealed(
    heroViewModel: HeroViewModel,
    modifier: Modifier = Modifier,
    heroUiStage: HeroInfo.Hero,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(heroUiStage.image),
            contentDescription = null,
            modifier = Modifier
                .clickable(
                    role = Role.Image,
                    onClick = { heroViewModel.showingCardFullScreen(heroUiStage) },
                )
                .border(2.dp, Color.White)
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = heroUiStage.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 6.sp,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .align(alignment = Alignment.TopCenter)
                    .size(width = 68.dp, height = 20.dp)
                    .background(Color(0x90000000))
                    .wrapContentSize()
            )
            Text(
                text = heroUiStage.effect,
                color = Color.White,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .size(width = 70.dp, height = 40.dp)
                    .background(Color(0x90000000))
                    .wrapContentSize()
            )
        }
    }
}

@Composable
private fun HeroHidden(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.card_null),
            contentDescription = null,
            modifier = Modifier
                .border(2.dp, Color.Black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle(modifier = Modifier.fillMaxSize())
    }
}