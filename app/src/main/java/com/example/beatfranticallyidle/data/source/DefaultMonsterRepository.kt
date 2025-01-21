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

    override suspend fun insertALL(allMonsters: List<MonsterEntity>) {
        monsterDao.insertAll(allMonsters)
    }

    override suspend fun updateMonster(nameMonster: String, deathCount: Int) {
        monsterDao.updateMonster(nameMonster, deathCount)
    }

    override fun getAllMonsters(): Flow<List<MonsterEntity>> {
        return monsterDao.getAllMonsters()
    }
}