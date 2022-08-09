package com.iuturakulov.uzbarchitecture_ar.ui.repository

import androidx.annotation.WorkerThread
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.network.ArchitectureClient
import com.iuturakulov.uzbarchitecture_ar.storage.dao.ArchitectureInfoDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val architectureClient: ArchitectureClient,
    private val architectureInfoDao: ArchitectureInfoDao
) {

    @WorkerThread
    fun fetchPokemonInfo(
        name: String,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow<ArchitectureInfo?> {
        val architectureInfo = architectureInfoDao.getArchitectureInfo(name)
        if (architectureInfo == null) {
            val response = architectureClient.fetchArchitectureInfo(name = name)
            response.suspendOnSuccess {
                architectureInfoDao.insertArchitectureInfo(data)
                emit(data)
            }.onError {
                onError("[Code: ${statusCode.code}]: ${message()}")
            }.onException {
                onError(message)
            }
        } else {
            emit(architectureInfo)
        }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}
