package com.example.beatfranticallyidle.game_ui.components.bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.beatfranticallyidle.HeroCardRoute
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.game_ui.theme.BeatFranticallyIdleTheme
import com.example.beatfranticallyidle.game_screen.main_screen.viewmodel.CardViewModel

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    backgroundColor: Color,
    cardViewModel: CardViewModel,
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
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(0.7f)
                    .clickable {
                        if (navController.currentDestination?.route
                            != HeroCardRoute.FireHero.route
                        ) {
                            navController.navigate(HeroCardRoute.FireHero.route)
                        }
                        cardViewModel.updateCardListForType(0)
                    }
            )
            Image(
                painter = painterResource(R.drawable.icone_poison),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .weight(1f)
                    .fillMaxSize(0.7f)
                    .clickable {
                        if (navController.currentDestination?.route
                            != HeroCardRoute.PoisonHero.route
                        ) {
                            navController.navigate(HeroCardRoute.PoisonHero.route)
                        }
                        cardViewModel.updateCardListForType(1)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesZonePreview() {
    BeatFranticallyIdleTheme {
        val navController: NavHostController = rememberNavController()
        val backgroundColor = Color.LightGray  // Ou qualquer outra cor
        // Cria uma instância mock do CardViewModel (sem lógica real)
        val cardViewModel: CardViewModel =
            viewModel()  // Tenta obter do contexto (pode precisar de ajustes)

        BottomBar(
            modifier = Modifier,
            navController = navController,
            backgroundColor = backgroundColor,
            cardViewModel = cardViewModel
        )
    }
}