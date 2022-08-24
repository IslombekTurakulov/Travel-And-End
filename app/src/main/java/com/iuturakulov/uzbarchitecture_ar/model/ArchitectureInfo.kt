package com.iuturakulov.uzbarchitecture_ar.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlin.random.Random

@Entity
@JsonClass(generateAdapter = true)
data class ArchitectureInfo(
    @field:Json(name = "id") @PrimaryKey var id: Int,
    @field:Json(name = "name") var name: String,
    @field:Json(name = "description") var description: String,
    @field:Json(name = "image_url") var imageUrl: String,
    @field:Json(name = "wikipedia_url") var wikipediaUrl: String,
    @field:Json(name = "ar_url") var arUrl: String,
)
