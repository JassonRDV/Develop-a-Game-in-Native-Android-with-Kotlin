package com.example.beatfranticallyidle.data.monster

import kotlinx.coroutines.flow.Flow

class MonsterRepository(private val monsterDao: MonsterDao) {
    suspend fun insert(item: MonsterEntity) = monsterDao.insert(item)

    suspend fun update(item: MonsterEntity) = monsterDao.update(item)

    suspend fun getMonsterById(id: Int): MonsterEntity? = monsterDao.getMonsterById(id)

    fun getAllMonsters(): Flow<List<MonsterEntity>> = monsterDao.getAllMonsters()
}