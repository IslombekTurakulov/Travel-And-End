package com.iuturakulov.uzbarchitecture_ar.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@JsonClass(generateAdapter = true)
@Parcelize
data class ArchitectureInfo(
    @field:Json(name = COLUMN_ARCH_ID) @PrimaryKey val id: Int,
    @field:Json(name = COLUMN_ARCH_NAME) val name: String,
    @field:Json(name = COLUMN_ARCH_DESC) val description: String,
    @field:Json(name = COLUMN_ARCH_IMAGE) val imageUrl: String,
    @field:Json(name = COLUMN_ARCH_WIKI) val wikipediaUrl: String,
    @field:Json(name = COLUMN_ARCH_AR) val arUrl: String,
) : Parcelable {
    companion object {
        const val COLUMN_ARCH_NAME = "name"
        const val COLUMN_ARCH_ID = "id"
        const val COLUMN_ARCH_DESC = "description"
        const val COLUMN_ARCH_IMAGE = "image_url"
        const val COLUMN_ARCH_WIKI = "wikipedia_url"
        const val COLUMN_ARCH_AR = "ar_url"
    }
}