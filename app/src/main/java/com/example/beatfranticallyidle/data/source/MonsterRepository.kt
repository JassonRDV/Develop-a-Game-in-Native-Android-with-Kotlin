package com.example.beatfranticallyidle.data.source

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beatfranticallyidle.data.source.local.monster.MonsterEntity
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {

    suspend fun insertALL(allMonsters: List<MonsterEntity>)

    suspend fun updateMonster(nameMonster: String, deathCount: Int)

    fun getAllMonsters(): Flow<List<MonsterEntity>>
}
