package com.example.beatfranticallyidle.data.source.local.monster

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MonsterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<MonsterEntity>)

    @Query("UPDATE monsters SET deathCount = :deathCount WHERE name = :nameMonster")
    suspend fun updateMonster(nameMonster: String, deathCount: Int)

    @Query("SELECT * FROM monsters")
    fun getAllMonsters(): Flow<List<MonsterEntity>>
}