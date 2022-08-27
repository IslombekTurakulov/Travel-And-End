package com.iuturakulov.uzbarchitecture_ar.ui.repository

import androidx.annotation.WorkerThread
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.network.ArchitectureClient
import com.iuturakulov.uzbarchitecture_ar.storage.ArchitectureInfoDao
import com.skydoves.sandwich.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val architectureClient: ArchitectureClient,
    private val architectureInfoDao: ArchitectureInfoDao
) : Repository {

    @WorkerThread
    fun fetchArchInfo(
        name: String,
        token: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow<ArchitectureInfo?> {
        val architectureInfo = architectureInfoDao.getArchitectureInfo(name)
        if (architectureInfo == null) {
            val response = architectureClient.fetchArchitectureInfo(name = name, token = token)
            response.suspendOnSuccess {
                architectureInfoDao.insertArchitectureInfo(data)
                emit(data)
            }.onError {
                onError("[Code ${statusCode}]: ${message()}")
            }.onException { onError(message) }
        } else {
            emit(architectureInfo)
        }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
