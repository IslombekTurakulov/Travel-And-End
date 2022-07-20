package com.iuturakulov.uzbarchitecture_ar.di

import android.app.Application
import androidx.room.Room
import com.iuturakulov.uzbarchitecture_ar.storage.AppDatabase
import com.iuturakulov.uzbarchitecture_ar.storage.TypeResponseConverter
import com.iuturakulov.uzbarchitecture_ar.storage.dao.ArchitectureDao
import com.iuturakulov.uzbarchitecture_ar.storage.dao.ArchitectureInfoDao
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
        typeResponseConverter: TypeResponseConverter
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "UzbArchitectureAR.db")
            .fallbackToDestructiveMigration()
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase): ArchitectureDao {
        return appDatabase.architectureDao()
    }

    @Provides
    @Singleton
    fun providePokemonInfoDao(appDatabase: AppDatabase): ArchitectureInfoDao {
        return appDatabase.architectureInfoDao()
    }

    @Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }
}
