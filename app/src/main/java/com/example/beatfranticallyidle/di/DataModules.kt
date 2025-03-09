package com.example.beatfranticallyidle.di

import android.content.Context
import androidx.room.Room
import com.example.beatfranticallyidle.data.source.local.AppDatabase
import com.example.beatfranticallyidle.data.source.local.card.CardDao
import com.example.beatfranticallyidle.data.source.local.card.CardRepository
import com.example.beatfranticallyidle.data.source.local.card.CardRepositoryImpl
import com.example.beatfranticallyidle.data.source.local.monster.MonsterDao
import com.example.beatfranticallyidle.data.source.local.monster.MonsterRepository
import com.example.beatfranticallyidle.data.source.local.monster.MonsterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMonsterRepository(impl: MonsterRepositoryImpl) : MonsterRepository

    @Binds
    @Singleton
    abstract fun bindCardRepository(impl: CardRepositoryImpl) : CardRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

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
    fun provideCardDao(appDataBase: AppDatabase): CardDao =
        appDataBase.cardDao()
}
