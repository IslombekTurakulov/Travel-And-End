package com.iuturakulov.uzbarchitecture_ar.network

import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class ArchitectureServiceImpl @Inject constructor(
    private val architectureService: ArchitectureService
) {

    suspend fun fetchArchitectureInfo(): ApiResponse<List<ArchitectureInfo>> {
        return architectureService.fetchArchitectureInfo()
    }
}
