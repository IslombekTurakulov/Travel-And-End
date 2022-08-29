package com.iuturakulov.uzbarchitecture_ar.ui.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.ui.repository.MainRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository,
) : BindingViewModel() {

    @get:Bindable
    var errorMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    private val architectureListFlow = mainRepository.getArchitectureList(
        onStart = { isLoading = false },
        onError = { errorMessage = it }
    )

    @get:Bindable
    val architectureList: List<ArchitectureInfo>? by architectureListFlow.asBindingProperty(
        viewModelScope,
        null
    )

    init {
        Timber.d("init MainViewModel")
    }
}
