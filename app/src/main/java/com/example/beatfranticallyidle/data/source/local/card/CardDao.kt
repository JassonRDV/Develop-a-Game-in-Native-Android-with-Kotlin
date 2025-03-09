package com.example.beatfranticallyidle.data.source.local.card

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCardTypes(cardTypes: List<CardTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCards(cards: List<CardEntity>)

    @Transaction
    @Query("SELECT * FROM card_elements")
    fun getAllCardsWithCardType(): Flow<List<CardWithCardType>>

    @Query("SELECT * FROM card_elements")
    fun getAllCardTypes(): Flow<List<CardTypeEntity>>

    @Query("SELECT * FROM cards")
    fun getAllCards(): Flow<List<CardEntity>>
}