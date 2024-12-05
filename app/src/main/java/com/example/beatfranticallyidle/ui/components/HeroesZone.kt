package com.example.beatfranticallyidle.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.data.card.CardInfo
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme

@Composable
fun HeroesZone(
    modifier: Modifier = Modifier,
    listHeroes: List<CardInfo.Card>,
    background: Int,
    paddingValues: PaddingValues
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
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = bottomPadding)
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                UnitCard(
                    heroCard = listHeroes[0].image,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = listHeroes[1].image,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = listHeroes[2].image,
                    modifier = modifier
                )
            }
            Row(
                modifier = Modifier.weight(1f)
            ) {
                UnitCard(
                    heroCard = listHeroes[3].image,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = listHeroes[4].image,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = listHeroes[5].image,
                    modifier = modifier
                )
            }
        }

    }
}

@Composable
fun UnitCard(
    heroCard: Int, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(heroCard),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color.White)
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