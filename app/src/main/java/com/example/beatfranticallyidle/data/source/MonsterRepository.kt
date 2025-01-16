package com.example.beatfranticallyidle.data.source

import com.example.beatfranticallyidle.data.source.local.monster.MonsterEntity
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {

    suspend fun insert(item: MonsterEntity)

    suspend fun update(item: MonsterEntity)

    suspend fun getMonsterById(id: Int): MonsterEntity?

    fun getAllMonsters(): Flow<List<MonsterEntity>>
}
