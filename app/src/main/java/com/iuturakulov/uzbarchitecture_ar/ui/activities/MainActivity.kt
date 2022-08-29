package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityMainBinding
import com.iuturakulov.uzbarchitecture_ar.network.ArchitectureClient
import com.iuturakulov.uzbarchitecture_ar.network.NetworkConnectivityObserver
import com.iuturakulov.uzbarchitecture_ar.storage.ArchitectureInfoDao
import com.iuturakulov.uzbarchitecture_ar.ui.adapter.ArchitectureAdapter
import com.iuturakulov.uzbarchitecture_ar.ui.viewmodel.MainViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var architectureClient: ArchitectureClient

    @Inject
    lateinit var architectureInfoDao: ArchitectureInfoDao

    @Inject
    lateinit var connectivityObserver: NetworkConnectivityObserver


    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@MainActivity
            adapter = ArchitectureAdapter()
            vm = viewModel
        }
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val response = architectureClient.fetchArchitectureInfo()
            response.suspendOnSuccess {
                data.forEach {
                    if (architectureInfoDao.getArchitecture(it.id) == null) {
                        architectureInfoDao.insertArchitectureInfo(it)
                    }
                }
            }.suspendOnError {
                Timber.d(message())
            }
        }
    }
}