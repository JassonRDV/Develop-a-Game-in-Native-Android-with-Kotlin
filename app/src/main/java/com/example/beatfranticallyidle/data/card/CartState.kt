package com.example.beatfranticallyidle.data.card

data class CartState(
    val currentCard: List<List<CardInfo.Card>> = listCard,
    val cardFullScreen: Boolean = false,
    val cardFullScreenCurrent: CardInfo.Card = listCard[0][0],
    val effectActivated: Boolean = false
)
