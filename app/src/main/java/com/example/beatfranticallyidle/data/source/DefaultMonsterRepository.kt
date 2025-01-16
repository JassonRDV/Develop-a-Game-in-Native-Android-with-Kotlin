package com.example.beatfranticallyidle.data.source

import com.example.beatfranticallyidle.data.source.local.monster.MonsterDao
import com.example.beatfranticallyidle.data.source.local.monster.MonsterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultMonsterRepository @Inject constructor(
    private val monsterDao: MonsterDao
) : MonsterRepository {

    override suspend fun insert(item: MonsterEntity) = monsterDao.insert(item)

    override suspend fun update(item: MonsterEntity) = monsterDao.update(item)

    override suspend fun getMonsterById(id: Int): MonsterEntity? = monsterDao.getMonsterById(id)

    override fun getAllMonsters(): Flow<List<MonsterEntity>> = monsterDao.getAllMonsters()
}