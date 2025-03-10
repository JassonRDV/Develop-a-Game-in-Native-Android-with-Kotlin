package com.example.beatfranticallyidle.data.source.local.card

import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.CardType
import com.example.beatfranticallyidle.data.source.local.card.model.CardWithCardType
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun insertAllCardTypes(cardTypes: List<CardType>)
    suspend fun insertAllCards(cards: List<Card>)
    fun getAllCardTypes(): Flow<List<CardType>>
    fun getAllCards(): Flow<List<Card>>
    fun getAllCardsWithCardType(): Flow<List<CardWithCardType>>
}