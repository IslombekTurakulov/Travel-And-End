package com.iuturakulov.uzbarchitecture_ar.ui.repository

import androidx.annotation.WorkerThread
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.network.ArchitectureClient
import com.iuturakulov.uzbarchitecture_ar.storage.ArchitectureInfoDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val architectureClient: ArchitectureClient,
    private val architectureInfoDao: ArchitectureInfoDao
) : Repository {

    @WorkerThread
    fun getArchitectureList(
        onStart: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<ArchitectureInfo>> =
        architectureInfoDao.getArchitectureInfo()
            .onEmpty { fetchArchInfo(onStart, onError) }
            .onStart { onStart() }
            .catch { onError(it.localizedMessage) }
            .flowOn(Dispatchers.IO)

    @WorkerThread
    fun fetchArchInfo(
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow<ArchitectureInfo?> {
        val response = architectureClient.fetchArchitectureInfo()
        response.suspendOnSuccess {
            data.onEach {
                architectureInfoDao.insertArchitectureInfo(it)
                emit(it)
            }
        }.onError {
            onError("[Code ${statusCode}]: ${message()}")
        }.onException { onError(message) }
    }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}

