package com.example.beatfranticallyidle.data.source.local.monster

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MonsterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun monsterDao(): MonsterDao
}