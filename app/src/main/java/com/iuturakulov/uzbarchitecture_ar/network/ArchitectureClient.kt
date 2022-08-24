package com.iuturakulov.uzbarchitecture_ar.network

import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class ArchitectureClient @Inject constructor(
    private val architectureService: ArchitectureService
) {

    suspend fun fetchArchitectureInfo(
        name: String,
        token: String
    ): ApiResponse<ArchitectureInfo> =
        architectureService.fetchArchitectureInfo(
            name = name,
            token = token
        )
}
