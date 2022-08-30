package com.iuturakulov.uzbarchitecture_ar.ui.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.ui.repository.MainRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : BindingViewModel() {

    @get:Bindable
    var errorMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set


    private val architectureListFlow = mainRepository.getArchitectureList(
        onStart = { isLoading = false },
        onError = { errorMessage = it })

    @get:Bindable
    val architectureList: List<ArchitectureInfo>? by architectureListFlow.asBindingProperty(
        viewModelScope,
        null
    )

    private val _archLiveData = MutableLiveData<List<ArchitectureInfo>?>()
    val architectureLiveData: LiveData<List<ArchitectureInfo>?>
        get() = _archLiveData

    init {
        Timber.d("init MainViewModel")
        viewModelScope.launch {
            mainRepository.updateArchitectureInfo().suspendOnSuccess {
                _archLiveData.value = data
                mainRepository.insertArchitectureInfo(data)
            }.suspendOnError {
                Timber.d("error ${message()}")
            }
        }
    }
}
