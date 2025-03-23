package com.example.beatfranticallyidle.data.source.local.monster

import java.math.BigDecimal
import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import kotlinx.coroutines.flow.Flow
import java.math.BigInteger

interface MonsterRepository {
    suspend fun insertAll(item: List<Monster>)
    suspend fun updateMonster(nameMonster: String, deathCount: BigInteger)
    fun getMonsters(): Flow<List<Monster>>
    fun getMonsterById(id: Int): Flow<Monster?>
    suspend fun insertMonster(monster: Monster)
    suspend fun deleteMonster(monster: Monster)
    suspend fun deleteAllMonsters()
}
