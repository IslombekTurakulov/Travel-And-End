package com.iuturakulov.uzbarchitecture_ar.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo

@Dao
interface ArchitectureInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchitectureInfo(architectureInfo: ArchitectureInfo)

    @Query("SELECT * FROM ArchitectureInfo WHERE name = :name_")
    suspend fun getArchitectureInfo(name_: String): ArchitectureInfo?
}
