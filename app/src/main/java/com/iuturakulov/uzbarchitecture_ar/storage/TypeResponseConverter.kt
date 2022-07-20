package com.iuturakulov.uzbarchitecture_ar.storage

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    private val moshi: Moshi
) {

    @TypeConverter
    fun fromInfoType(type: List<ArchitectureInfo.TypeResponse>?): String {
        val listType =
            Types.newParameterizedType(List::class.java, ArchitectureInfo.TypeResponse::class.java)
        val adapter: JsonAdapter<List<ArchitectureInfo.TypeResponse>> = moshi.adapter(listType)
        return adapter.toJson(type)
    }
}
