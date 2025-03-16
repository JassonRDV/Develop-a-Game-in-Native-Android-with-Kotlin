package com.example.beatfranticallyidle.data.source.local.monster

import android.icu.math.BigDecimal
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.math.BigInteger

@Dao
interface MonsterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<MonsterEntity>)

    @Query("UPDATE monsters SET deathCount = :deathCount WHERE name = :nameMonster")
    suspend fun updateMonster(nameMonster: String, deathCount: BigInteger)

    @Query("SELECT * FROM monsters")
    fun getMonsters(): Flow<List<MonsterEntity>>

    @Query("SELECT * FROM monsters WHERE id = :id")
    fun getMonsterById(id: Int): Flow<MonsterEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonster(monster: MonsterEntity)

    @Delete
    suspend fun deleteMonster(monster: MonsterEntity)

    @Query("DELETE FROM monsters")
    suspend fun deleteAllMonsters()
}