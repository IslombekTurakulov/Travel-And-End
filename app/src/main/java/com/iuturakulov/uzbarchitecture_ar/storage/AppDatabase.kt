package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.storage.dao.ArchitectureDao
import com.iuturakulov.uzbarchitecture_ar.storage.dao.ArchitectureInfoDao

@Database(entities = [Architecture::class, ArchitectureInfo::class], version = 1, exportSchema = true)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun architectureDao(): ArchitectureDao

  abstract fun architectureInfoDao(): ArchitectureInfoDao
}