package com.example.beatfranticallyidle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.beatfranticallyidle.data.monster.MonsterDao
import com.example.beatfranticallyidle.data.monster.MonsterEntity
import com.example.beatfranticallyidle.data.monster.MonsterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [MonsterEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract val monsterDao: MonsterDao
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMonsterDao(appDataBase: AppDataBase): MonsterDao {
        return appDataBase.monsterDao
    }

    @Provides
    fun provideMonsterRepository(monsterDao: MonsterDao): MonsterRepository {
        return MonsterRepository(monsterDao)
    }
}