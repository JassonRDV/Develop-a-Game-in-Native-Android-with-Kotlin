package com.example.beatfranticallyidle.game.cardeffect

import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal

interface CardEffect {
    fun applyEffect(card: Card, target: Monster, scope: CoroutineScope)
}

class FireCardEffect(private val damage: BigDecimal) : CardEffect {
    override fun applyEffect(card: Card, target: Monster, scope: CoroutineScope) {
        scope.launch {
            while (target.isAlive() && card.discovered) {
                target.takeDamage(damage)
                delay(1000)
            }
        }
    }
}