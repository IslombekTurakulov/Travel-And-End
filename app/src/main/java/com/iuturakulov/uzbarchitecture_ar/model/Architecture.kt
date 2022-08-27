package com.iuturakulov.uzbarchitecture_ar.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Architecture(
    @PrimaryKey @ColumnInfo(name = ArchitectureInfo.COLUMN_ARCH_ID) val id: Int,
    @ColumnInfo(name = ArchitectureInfo.COLUMN_ARCH_NAME) val name: String,
    @ColumnInfo(name = ArchitectureInfo.COLUMN_ARCH_IMAGE) val url: String
) : Parcelable