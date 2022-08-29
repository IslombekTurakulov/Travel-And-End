package com.iuturakulov.uzbarchitecture_ar.ui.repository

import androidx.annotation.WorkerThread
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.network.ArchitectureClient
import com.iuturakulov.uzbarchitecture_ar.storage.ArchitectureInfoDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val architectureClient: ArchitectureClient,
    private val architectureInfoDao: ArchitectureInfoDao
) {

    @WorkerThread
    fun getArchitectureList(
        onStart: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<ArchitectureInfo>> {
        runBlocking {
            insertArch(onError = onError)
        }
        return architectureInfoDao.getArchitectureInfo()
            .onStart { onStart() }
            .catch { onError(it.localizedMessage) }
            .flowOn(Dispatchers.IO)
    }


    @WorkerThread
    suspend fun insertArch(
        onError: (String?) -> Unit
    ) {
        val response = architectureClient.fetchArchitectureInfo()
        response.suspendOnSuccess {
            data.forEach {
                architectureInfoDao.insertArchitectureInfo(it)
            }
        }.suspendOnError {
            onError(message())
        }
    }
}

