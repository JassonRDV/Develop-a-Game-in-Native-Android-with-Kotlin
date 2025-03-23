package com.example.beatfranticallyidle.ui.screen.game.mainscreen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.genericHero
import com.example.beatfranticallyidle.viewmodel.CardUiState
import com.example.beatfranticallyidle.viewmodel.CardViewModel
import com.example.beatfranticallyidle.viewmodel.SoundsViewModel

@Composable
fun HeroesZone(
    background: Int,
    paddingValues: PaddingValues,
    cardViewModel: CardViewModel,
    currentCard: CardUiState,
    modifier: Modifier = Modifier,
    soundsViewModel: SoundsViewModel,
) {
    val bottomPadding = paddingValues.calculateBottomPadding()
    val cardList = currentCard.listCard ?: emptyList()
    val card = List(6) { index ->
        cardList.getOrNull(index) ?: genericHero
    }
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
            card.chunked(3).forEach { rowCards ->
                Row(modifier = Modifier.weight(1f)) {
                    rowCards.forEach { card ->
                        UnitCard(
                            soundsViewModel = soundsViewModel,
                            modifier = modifier,
                            currentCard = card,
                            cardViewModel = cardViewModel,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UnitCard(
    currentCard: Card,
    cardViewModel: CardViewModel,
    modifier: Modifier = Modifier,
    soundsViewModel: SoundsViewModel,
) {
    when(currentCard.discovered) {
        true -> HeroRevealed(
            soundsViewModel = soundsViewModel,
            cardViewModel = cardViewModel,
            currentHero = currentCard,
            modifier = modifier.fillMaxSize(),
        )
        else -> HeroHidden(
            currentHero = currentCard,
            modifier = modifier
        )
    }
}

@Composable
private fun HeroRevealed(
    currentHero: Card,
    cardViewModel: CardViewModel,
    modifier: Modifier = Modifier,
    soundsViewModel: SoundsViewModel,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .clickable(
                role = Role.Image,
                onClick = {
                    cardViewModel.showingCardFullScreen(currentHero)
                    soundsViewModel.playSoundEffect(1)
                },
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(currentHero.imageResId),
            contentDescription = null,
            modifier = Modifier
                .border(2.dp, Color.White)
        )
        Box(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
        ) {
            Text(
                text = currentHero.name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                    .clip(RoundedCornerShape(4.dp))
                    .align(alignment = Alignment.TopCenter)
                    .background(Color(0x90000000))
                    .wrapContentSize()
            )
            Text(
                text = currentHero.effectDescription,
                color = Color.White,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        end = 8.dp
                    )
                    .clip(RoundedCornerShape(4.dp))
                    .align(alignment = Alignment.BottomCenter)
                    .background(Color(0x90000000))
                    .wrapContentSize()
            )
        }
    }
}

@Composable
private fun HeroHidden(
    modifier: Modifier = Modifier,
    currentHero: Card
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(currentHero.imageTypeResId),
            contentDescription = null,
            modifier = Modifier
                .border(
                    2.dp,
                    Color.White
                )
        )
    }
}