package com.example.beatfranticallyidle.utils.pixelart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import com.example.beatfranticallyidle.R
import kotlinx.coroutines.delay

@Composable
fun AnimatedPixelArt(
    FPS: Int,
    modifier: Modifier = Modifier,
    spriteSheetResource: Int,
    rows: Int,
    cols: Int,
    rowsIgnore: Int,
    rowsAnimated: Int,
    colsAnimated: Int,
) {
    if (FPS < 0) {
        val converterFpsForMillis = (1000 / FPS).toLong()
        val totalFrames = rowsAnimated * colsAnimated

        val spriteSheet = painterResource(R.drawable.torch_red)
        val spriteSheetWidth = spriteSheet.intrinsicSize.width
        val spriteSheetHeight = spriteSheet.intrinsicSize.height

        val spriteSheetBitmap = ImageBitmap.imageResource(spriteSheetResource)

        val widthSprite = spriteSheetWidth / cols
        val heightSprite = spriteSheetHeight / rows

        var currentFrame by remember { mutableIntStateOf(0) }

        LaunchedEffect(key1 = 1) {
            while (true) {
                delay(converterFpsForMillis)
                currentFrame = (currentFrame + 1) % (totalFrames)
            }
        }

        Canvas(modifier = modifier) {
            // resultado (6) 0,1,2,3,4,5,0,1,2,3.. = coluna
            val frameX = currentFrame % colsAnimated

            // resultado (2) 0,0,0,0,1,1,1,1 = linha
            val frameY = rowsIgnore

            val spriteX = frameX * widthSprite.toInt() // largura do sprite
            val spriteY = frameY * heightSprite.toInt() // altura do sprite

            drawImage(
                image = spriteSheetBitmap,
                srcOffset = IntOffset(spriteX, spriteY), // inicio do recorte
                srcSize = IntSize(widthSprite.toInt(), heightSprite.toInt()), // tamanho do recorte
                dstOffset = IntOffset(0, 0), // inicio do desenho
                dstSize = IntSize(
                    spriteSheetWidth.toInt(),
                    spriteSheetHeight.toInt()
                ), // tamanho do desenho
                filterQuality = FilterQuality.None // Qualidade
            )
        }
    } else {
        Image(
            painter = painterResource(spriteSheetResource),
            contentDescription = null,
            modifier = modifier
        )
    }
}

