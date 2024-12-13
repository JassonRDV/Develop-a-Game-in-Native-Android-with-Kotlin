package com.example.beatfranticallyidle.ui.components.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.beatfranticallyidle.AppIdle
import com.example.beatfranticallyidle.R
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = backgroundColor
            ),
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    maxLines = 1,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MonsterZonePreview() {
    BeatFranticallyIdleTheme {
        AppIdle(modifier = Modifier.fillMaxSize())
    }
}