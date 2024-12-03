package com.example.beatfranticallyidle.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.beatfranticallyidle.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.height(56.dp).border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.parede_de_pedra),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(350.dp)
        )
        CenterAlignedTopAppBar(
            modifier = modifier,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent
            ),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }
            }
        )
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.border(1.dp, Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.parede_de_pedra),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(350.dp)
        )
        BottomAppBar(
            modifier = modifier,
            containerColor = Color.Transparent,
            contentColor = Color.Black
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Construction,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Construction,
                        contentDescription = null
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Construction,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun MonsterBoard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.border(1.dp, Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.monsterarena),
            contentScale = ContentScale.FillHeight,
            contentDescription = null
        )
        Image(
            painter = painterResource(R.drawable.monstergalo),
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )
    }
}
