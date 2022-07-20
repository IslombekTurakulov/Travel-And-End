package com.iuturakulov.uzbarchitecture_ar.network

import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArchitectureService {

  @GET("archcomp/{name}")
  suspend fun fetchArchitectureInfo(@Path("name") name: String): ApiResponse<ArchitectureInfo>
}
