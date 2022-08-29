package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchitectureInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchitectureInfo(architectureInfo: ArchitectureInfo)

    @Query("SELECT * FROM ArchitectureInfo ORDER BY id ASC")
    fun getArchitectureInfo(): Flow<List<ArchitectureInfo>>
}
