package com.iuturakulov.uzbarchitecture_ar.di

import android.app.Application
import androidx.room.Room
import com.iuturakulov.uzbarchitecture_ar.storage.AppDatabase
import com.iuturakulov.uzbarchitecture_ar.storage.ArchitectureDao
import com.iuturakulov.uzbarchitecture_ar.storage.ArchitectureInfoDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "uzb-architecture-ar.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArchDao(appDatabase: AppDatabase): ArchitectureDao {
        return appDatabase.architectureDao()
    }

    @Provides
    @Singleton
    fun provideArchInfoDao(appDatabase: AppDatabase): ArchitectureInfoDao {
        return appDatabase.architectureInfoDao()
    }
}
