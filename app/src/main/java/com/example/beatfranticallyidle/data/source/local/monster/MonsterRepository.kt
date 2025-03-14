package com.example.beatfranticallyidle.data.source.local.monster

import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import kotlinx.coroutines.flow.Flow

interface MonsterRepository {
    suspend fun insertAll(item: List<Monster>)
    suspend fun updateMonster(nameMonster: String, deathCount: Int)
    fun getMonsters(): Flow<List<Monster>>
    fun getMonsterById(id: Int): Flow<Monster?>
    suspend fun insertMonster(monster: Monster)
    suspend fun deleteMonster(monster: Monster)
    suspend fun deleteAllMonsters()
}
