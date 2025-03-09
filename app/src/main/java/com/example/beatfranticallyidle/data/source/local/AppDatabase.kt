package com.example.beatfranticallyidle.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.beatfranticallyidle.data.source.local.card.CardDao
import com.example.beatfranticallyidle.data.source.local.card.CardEntity
import com.example.beatfranticallyidle.data.source.local.card.CardTypeEntity
import com.example.beatfranticallyidle.data.source.local.card.model.CardEffect
import com.example.beatfranticallyidle.data.source.local.card.model.CardElement
import com.example.beatfranticallyidle.data.source.local.monster.MonsterDao
import com.example.beatfranticallyidle.data.source.local.monster.MonsterEntity
import com.example.beatfranticallyidle.data.source.local.monster.model.RewardType

@Database(
    entities = [MonsterEntity::class, CardTypeEntity::class, CardEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun monsterDao(): MonsterDao
    abstract fun cardDao(): CardDao
}

class Converters {

    @androidx.room.TypeConverter
    fun fromRewardType(value: RewardType): String {
        return value.name
    }

    @androidx.room.TypeConverter
    fun toRewardType(value: String): RewardType {
        return enumValueOf(value)
    }

    @androidx.room.TypeConverter
    fun fromCardElement(value: CardElement): String {
        return value.name
    }

    @androidx.room.TypeConverter
    fun toCardElement(value: String): CardElement {
        return enumValueOf(value)
    }

    @androidx.room.TypeConverter
    fun fromCardEffect(value: CardEffect): String {
        return value.name
    }

    @androidx.room.TypeConverter
    fun toCardEffect(value: String): CardEffect {
        return enumValueOf(value)
    }
}