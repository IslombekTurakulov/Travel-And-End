package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Dao
import androidx.room.Query
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchitectureDao {

    @Query("SELECT * FROM ${ArchitectureInfo.TABLE_NAME} ORDER BY ${ArchitectureInfo.COLUMN_ARCH_ID} ASC")
    fun getArchitectureList(): Flow<List<Architecture>>
}
