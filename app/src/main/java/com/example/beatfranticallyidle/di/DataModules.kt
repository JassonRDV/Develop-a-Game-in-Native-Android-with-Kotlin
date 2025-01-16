package com.example.beatfranticallyidle.di

import android.content.Context
import androidx.room.Room
import com.example.beatfranticallyidle.data.source.DefaultMonsterRepository
import com.example.beatfranticallyidle.data.source.local.monster.AppDatabase
import com.example.beatfranticallyidle.data.source.local.monster.MonsterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMonsterDao(appDataBase: AppDatabase): MonsterDao =
        appDataBase.monsterDao()


    @Provides
    fun provideMonsterRepository(monsterDao: MonsterDao): DefaultMonsterRepository =
        DefaultMonsterRepository(monsterDao)
}