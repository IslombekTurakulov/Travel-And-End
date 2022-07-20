package com.iuturakulov.uzbarchitecture_ar.extensions

import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message


object ErrorResponseMapper : ApiErrorModelMapper<ArchitectureErrorResponse> {
  override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ArchitectureErrorResponse {
    return ArchitectureErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
  }
}
