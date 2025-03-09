package com.example.beatfranticallyidle.data.source.local.card

import androidx.annotation.DrawableRes
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.beatfranticallyidle.data.source.local.card.model.Card
import com.example.beatfranticallyidle.data.source.local.card.model.CardEffect
import com.example.beatfranticallyidle.data.source.local.card.model.CardElement
import com.example.beatfranticallyidle.data.source.local.card.model.CardType

@Entity(tableName = "card_elements")
data class CardTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val cardElement: CardElement
) {
    fun toCardType(): CardType {
        return CardType(
            id = id,
            cardElement = cardElement
        )
    }
}

@Entity(
    tableName = "cards",
    foreignKeys = [
        ForeignKey(
            entity = CardTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["cardElementId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("cardElementId")]
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @DrawableRes val imageResId: Int,
    val name: String,
    val cardElementId: Int,
    val effect: CardEffect,
    val effectDescription: String,
    val discovered: Boolean,
    val effectActivated: Boolean,
    val numberCardCount: Int,
    val imageTypeResId: Int
) {
    fun toCard(): Card {
        return Card(
            id = id,
            imageResId = imageResId,
            name = name,
            effect = effect,
            effectDescription = effectDescription,
            discovered = discovered,
            effectActivated = effectActivated,
            numberCardCount = numberCardCount,
            cardElementId = cardElementId,
            imageTypeResId = imageTypeResId,
        )
    }
}

data class CardWithCardType(
    @Embedded val cardType: CardTypeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cardElementId"
    )
    val cards: List<CardEntity>
)