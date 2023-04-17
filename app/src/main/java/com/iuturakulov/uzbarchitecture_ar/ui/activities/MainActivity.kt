package com.iuturakulov.uzbarchitecture_ar.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ActivityMainBinding
import com.iuturakulov.uzbarchitecture_ar.ui.adapter.ArchitectureCompactAdapter
import com.iuturakulov.uzbarchitecture_ar.ui.adapter.ArchitectureFullAdapter
import com.iuturakulov.uzbarchitecture_ar.ui.viewmodel.MainViewModel
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding {
            lifecycleOwner = this@MainActivity
            compactAdapter = ArchitectureCompactAdapter()
            fullAdapter = ArchitectureFullAdapter()
            vm = viewModel
        }
        viewModel.architectureLiveData.observe(this) { archs ->
            archs?.let { binding.compactAdapter?.setArchList(archs) }
        }
        viewModel.architectureLiveData.observe(this) { archs ->
            archs?.let { binding.fullAdapter?.setArchList(archs) }
        }
    }
}