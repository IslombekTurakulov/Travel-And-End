package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import kotlinx.coroutines.flow.Flow

@Dao
interface ArchitectureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArchitecture(architecture: Architecture)

    @Query("SELECT * FROM Architecture WHERE id = :id_")
    suspend fun getArchitecture(id_: Int): Architecture?

    @Query("SELECT * FROM Architecture")
    fun getArchitectureList(): Flow<List<Architecture>>
}
