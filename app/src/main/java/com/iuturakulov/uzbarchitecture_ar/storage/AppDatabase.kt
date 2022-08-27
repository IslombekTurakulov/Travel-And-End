package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo

@Database(
    entities = [Architecture::class, ArchitectureInfo::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun architectureDao(): ArchitectureDao

    abstract fun architectureInfoDao(): ArchitectureInfoDao
}
