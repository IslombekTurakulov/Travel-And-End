package com.iuturakulov.uzbarchitecture_ar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo.Companion.COLUMN_ARCH_NAME
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo.Companion.TABLE_NAME
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(
        value = [COLUMN_ARCH_NAME],
        unique = true
    )]
)
@JsonClass(generateAdapter = true)
data class ArchitectureInfo(
    @field:Json(name = COLUMN_ARCH_ID) @PrimaryKey @ColumnInfo(name = COLUMN_ARCH_ID) val id: Int,
    @field:Json(name = COLUMN_ARCH_NAME) @ColumnInfo(name = COLUMN_ARCH_NAME) val name: String,
    @field:Json(name = COLUMN_ARCH_DESC) @ColumnInfo(name = COLUMN_ARCH_DESC) val description: String,
    @field:Json(name = COLUMN_ARCH_IMAGE) @ColumnInfo(name = COLUMN_ARCH_IMAGE) val imageUrl: String,
    @field:Json(name = COLUMN_ARCH_WIKI) @ColumnInfo(name = COLUMN_ARCH_WIKI) val wikipediaUrl: String,
    @field:Json(name = COLUMN_ARCH_AR) @ColumnInfo(name = COLUMN_ARCH_AR) val arUrl: String,
) {
    companion object {
        const val TABLE_NAME = "architecture_info"
        const val COLUMN_ARCH_NAME = "name"
        const val COLUMN_ARCH_ID = "title"
        const val COLUMN_ARCH_DESC = "description"
        const val COLUMN_ARCH_IMAGE = "image_url"
        const val COLUMN_ARCH_WIKI = "wikipedia_url"
        const val COLUMN_ARCH_AR = "ar_url"
    }
}