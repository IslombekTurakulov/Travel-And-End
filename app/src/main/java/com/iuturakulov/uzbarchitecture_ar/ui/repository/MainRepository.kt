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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val architectureClient: ArchitectureClient,
    private val architectureInfoDao: ArchitectureInfoDao
) : Repository {

    @WorkerThread
    suspend fun getArchitectureList(
        onStart: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<ArchitectureInfo>> {
        val response = architectureClient.fetchArchitectureInfo()
        // No idea how to call this flow correctly :(
        response.suspendOnSuccess {
            data.forEach {
                if (architectureInfoDao.getArchitecture(it.id) == null) {
                    architectureInfoDao.insertArchitectureInfo(it)
                }
            }
        }.onError {
            onError("[Code ${statusCode}]: ${message()}")
        }.onException { onError(message) }
        return architectureInfoDao.getArchitectureInfo()
            .onStart { onStart() }
            .catch { onError(it.localizedMessage) }
            .flowOn(Dispatchers.IO)
    }

}

