package com.example.beatfranticallyidle.data.source.local.card

import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.CardType
import com.example.beatfranticallyidle.data.source.local.card.model.CardWithCardType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
) : CardRepository {

    override suspend fun insertAllCardTypes(cardTypes: List<CardType>)  =
        cardDao.insertAllCardTypes(cardTypes.map { it.toCardTypeEntity() })

    override suspend fun insertAllCards(cards: List<Card>) =
        cardDao.insertAllCards(cards.map { it.toCardEntity() })

    override fun getAllCardTypes(): Flow<List<CardType>> =
        cardDao.getAllCardTypes().map { cardTypeEntities ->
            cardTypeEntities.map { it.toCardType() }
        }

    override fun getAllCards(): Flow<List<Card>> =
        cardDao.getAllCards().map { cardEntities ->
            cardEntities.map { it.toCard() }
        }

    override fun getAllCardsWithCardType(): Flow<List<CardWithCardType>> =
        cardDao.getAllCardsWithCardType().map { cardWithCardTypeEntities ->
            cardWithCardTypeEntities.map { it.toCardWithCardType() }
        }

    override suspend fun updateCard(
        id: Int,
        discovered: Boolean,
        effectActivated: Boolean,
    ) = cardDao.updateCard(id, discovered, effectActivated)
}