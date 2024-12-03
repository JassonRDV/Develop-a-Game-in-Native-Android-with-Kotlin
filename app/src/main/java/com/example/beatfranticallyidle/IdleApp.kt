package com.example.beatfranticallyidle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.beatfranticallyidle.screen.BottomBar
import com.example.beatfranticallyidle.screen.CardBoard
import com.example.beatfranticallyidle.screen.MonsterBoard
import com.example.beatfranticallyidle.screen.TopBar

@Composable
fun AppIdle(modifier: Modifier = Modifier) {
    Scaffold(
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
        topBar = {
            TopBar(modifier = Modifier)
        },
        bottomBar = {
            BottomBar(modifier = Modifier.height(92.dp))
        },
    ) { paddingValues ->
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color.Transparent,
                    modifier = Modifier
                        .width(100.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.parede_de_madeira),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(800.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.RestaurantMenu,
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
        ) {
            Column(
                modifier = modifier.padding(paddingValues)
            ) {
                MonsterBoard(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
                CardBoard(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
            }
        }
    }
}
