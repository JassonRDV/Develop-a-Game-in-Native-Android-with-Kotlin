package com.example.beatfranticallyidle.data.source.local.monster

import com.example.beatfranticallyidle.data.source.local.monster.model.Monster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonsterRepositoryImpl @Inject constructor(
    private val monsterDao: MonsterDao
) : MonsterRepository {

    override fun getMonsters(): Flow<List<Monster>> =
        monsterDao.getMonsters().map { list ->
            list.map { it.toMonster() }
        }

    override suspend fun insertAll(item: List<Monster>) =
        monsterDao.insertAll(item.map { it.toMonsterEntity() })

    override suspend fun updateMonster(nameMonster: String, deathCount: Int) =
        monsterDao.updateMonster(nameMonster, deathCount)

    override fun getMonsterById(id: Int): Flow<Monster?> =
        monsterDao.getMonsterById(id).map { it?.toMonster() }

    override suspend fun insertMonster(monster: Monster) =
        monsterDao.insertMonster(monster.toMonsterEntity())

    override suspend fun deleteMonster(monster: Monster) =
        monsterDao.deleteMonster(monster.toMonsterEntity())

    override suspend fun deleteAllMonsters() =
        monsterDao.deleteAllMonsters()
}