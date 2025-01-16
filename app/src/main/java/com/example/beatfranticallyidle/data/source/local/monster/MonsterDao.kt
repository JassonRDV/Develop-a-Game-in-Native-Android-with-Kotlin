package com.example.beatfranticallyidle.data.source.local.monster

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MonsterDao {
    @Insert
    suspend fun insert(item: MonsterEntity)

    @Update
    suspend fun update(item: MonsterEntity)

    // Maybe I don't need this function anymore
    @Query("SELECT * FROM monsters WHERE id = :id")
    suspend fun getMonsterById(id: Int): MonsterEntity?

    // Maybe I dont't need this function anymore
    @Query("SELECT * FROM monsters")
    fun getAllMonsters(): Flow<List<MonsterEntity>>
}