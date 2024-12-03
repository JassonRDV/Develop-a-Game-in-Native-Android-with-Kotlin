package com.example.beatfranticallyidle.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.beatfranticallyidle.R

@Composable
fun CardBoard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize().border(1.dp, Color.Black)
    ) {
        Image(
            painterResource(R.drawable.parede_de_madeira),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = modifier.weight(1f)
            ) {
                UnitCard(
                    heroCard = R.drawable.espiritofogo,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = R.drawable.dogfogo,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = R.drawable.argueirofogo,
                    modifier = modifier
                )
            }
            Row(
                modifier = modifier.weight(1f)
            ) {
                UnitCard(
                    heroCard = R.drawable.magofogo,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = R.drawable.dragaofogo,
                    modifier = modifier
                )
                UnitCard(
                    heroCard = R.drawable.guerreirofogo,
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
                .padding(8.dp).border(2.dp, Color.Black)

        )
    }
}