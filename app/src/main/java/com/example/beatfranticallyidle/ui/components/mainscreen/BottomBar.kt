package com.example.beatfranticallyidle.ui.components.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.beatfranticallyidle.HeroCardRoute
import com.example.beatfranticallyidle.R

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    backgroundColor: Color
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        BottomAppBar(
            modifier = modifier,
            containerColor = backgroundColor
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = painterResource(R.drawable.icone_fogo),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (navController.currentDestination?.route
                            != HeroCardRoute.FireHero.route)
                            navController.navigate(HeroCardRoute.FireHero.route)
                    }
                )
                Image(
                    painter = painterResource(R.drawable.icone_poison),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        if (navController.currentDestination?.route
                            != HeroCardRoute.PoisonHero.route)
                        navController.navigate(HeroCardRoute.PoisonHero.route) }
                )
            }
        }
    }
}
