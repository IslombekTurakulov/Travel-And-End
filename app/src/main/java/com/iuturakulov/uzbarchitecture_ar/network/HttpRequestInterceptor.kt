package com.iuturakulov.uzbarchitecture_ar.network

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class HttpRequestInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val origRequest = chain.request()
    val request = origRequest.newBuilder().url(origRequest.url).build()
    Timber.d(request.toString())
    return chain.proceed(request)
  }
}
