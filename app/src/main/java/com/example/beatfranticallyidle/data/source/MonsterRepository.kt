package com.example.beatfranticallyidle.data.source

import com.example.beatfranticallyidle.data.source.local.monster.MonsterEntity
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {

    suspend fun insertALL(allMonsters: List<MonsterEntity>)

    suspend fun deleteAll(allMonsters: List<MonsterEntity>)

    suspend fun update(monster: MonsterEntity)

    suspend fun getMonsterById(id: Int): MonsterEntity?

    fun getAllMonsters(): Flow<List<MonsterEntity>>
}
