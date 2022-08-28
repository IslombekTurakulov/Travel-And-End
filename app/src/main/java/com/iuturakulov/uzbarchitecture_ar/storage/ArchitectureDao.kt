package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Dao
import androidx.room.Query
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchitectureDao {

    @Query("SELECT * FROM Architecture ORDER BY name ASC")
    fun getArchitectureList(): Flow<List<Architecture>>
}
