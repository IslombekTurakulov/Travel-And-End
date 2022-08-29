package com.iuturakulov.uzbarchitecture_ar.network

import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ArchitectureService {

    @GET("architectures_en.json")
    suspend fun fetchArchitectureInfo(): ApiResponse<List<ArchitectureInfo>>
}
