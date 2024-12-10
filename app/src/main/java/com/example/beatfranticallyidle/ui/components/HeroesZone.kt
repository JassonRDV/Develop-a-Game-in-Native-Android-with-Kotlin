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
import com.example.beatfranticallyidle.data.card.CardInfo
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.viewmodel.CardViewModel

@Composable
fun HeroesZone(
    modifier: Modifier = Modifier,
    listHeroes: List<CardInfo.Card>,
    background: Int,
    paddingValues: PaddingValues,
    cardUiStage: List<CardInfo.Card>,
    cardViewModel: CardViewModel
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
                    currentCard = listHeroes[0],
                    cardViewModel = cardViewModel,
                    effectUiStage = cardUiStage[0].effect,
                    nameUiStage = cardUiStage[0].name,
                    heroCard = listHeroes[0].image,
                    modifier = modifier
                )
                UnitCard(
                    currentCard = listHeroes[1],
                    heroCard = listHeroes[1].image,
                    modifier = modifier,
                    cardViewModel = cardViewModel,
                    effectUiStage = cardUiStage[1].effect,
                    nameUiStage = cardUiStage[1].name
                )
                UnitCard(
                    currentCard = listHeroes[2],
                    heroCard = listHeroes[2].image,
                    modifier = modifier,
                    cardViewModel = cardViewModel,
                    effectUiStage = cardUiStage[2].effect,
                    nameUiStage = cardUiStage[2].name
                )
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                UnitCard(
                    currentCard = listHeroes[3],
                    heroCard = listHeroes[3].image,
                    modifier = modifier,
                    cardViewModel = cardViewModel,
                    effectUiStage = cardUiStage[3].effect,
                    nameUiStage = cardUiStage[3].name
                )
                UnitCard(
                    currentCard = listHeroes[4],
                    heroCard = listHeroes[4].image,
                    modifier = modifier,
                    cardViewModel = cardViewModel,
                    effectUiStage = cardUiStage[4].effect,
                    nameUiStage = cardUiStage[4].name
                )
                UnitCard(
                    currentCard = listHeroes[5],
                    heroCard = listHeroes[5].image,
                    modifier = modifier,
                    cardViewModel = cardViewModel,
                    effectUiStage = cardUiStage[5].effect,
                    nameUiStage = cardUiStage[5].name
                )
            }
        }
    }
}

@Composable
fun UnitCard(
    currentCard: CardInfo.Card,
    heroCard: Int,
    modifier: Modifier = Modifier,
    cardViewModel: CardViewModel,
    effectUiStage: String,
    nameUiStage: String
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(heroCard),
            contentDescription = null,
            modifier = Modifier
                .clickable(
                    role = Role.Image,
                    onClick = { cardViewModel.showingCardFullScreen(currentCard) },
                )
                .border(2.dp, Color.White)
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = nameUiStage,
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
                text = effectUiStage,
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

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle(modifier = Modifier.fillMaxSize())
    }
}