package com.iuturakulov.uzbarchitecture_ar.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Architecture(
    @PrimaryKey val id: Int,
    val name: String,
    val url: String
) : Parcelable