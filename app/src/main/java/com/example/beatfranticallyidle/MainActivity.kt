package com.example.beatfranticallyidle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beatfranticallyidle.screen.AppIdle
import com.example.beatfranticallyidle.screen.BottomAppBar
import com.example.beatfranticallyidle.screen.TopAppBar
import com.example.beatfranticallyidle.ui.theme.BeatFranticallyIdleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeatFranticallyIdleTheme {
                AppIdle()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppIdle() {
    BeatFranticallyIdleTheme {
        AppIdle()
    }
}


