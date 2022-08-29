package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo

@Database(
    entities = [ArchitectureInfo::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun architectureInfoDao(): ArchitectureInfoDao
}
