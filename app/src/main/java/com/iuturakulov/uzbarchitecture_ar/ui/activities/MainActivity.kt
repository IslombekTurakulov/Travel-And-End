package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityMainBinding
import com.iuturakulov.uzbarchitecture_ar.network.NetworkConnectivityObserver
import com.iuturakulov.uzbarchitecture_ar.ui.adapter.ArchitectureAdapter
import com.iuturakulov.uzbarchitecture_ar.ui.viewmodel.MainViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()

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
}