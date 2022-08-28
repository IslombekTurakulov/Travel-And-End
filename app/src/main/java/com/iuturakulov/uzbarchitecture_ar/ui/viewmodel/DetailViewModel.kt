package com.iuturakulov.uzbarchitecture_ar.ui.viewmodel

import androidx.databinding.Bindable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.ui.repository.DetailRepository
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.bindables.bindingProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber

class DetailViewModel @AssistedInject constructor(
    detailRepository: DetailRepository,
    @Assisted private val archName: String,
    @Assisted private val token: String
) : BindingViewModel() {

    private val archInfoFlow = detailRepository.fetchArchInfo(
        name = archName,
        token = token,
        onComplete = { isLoading = false },
        onError = { errorMessage = it }
    )

    @get:Bindable
    val architectureInfo: ArchitectureInfo? by archInfoFlow.asBindingProperty(viewModelScope, null)

    @get:Bindable
    var errorMessage: String? by bindingProperty(null)
        private set

    @get:Bindable
    var isLoading: Boolean by bindingProperty(true)
        private set

    init {
        Timber.d("init DetailViewModel")
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(archName: String): DetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            archName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(archName) as T
            }
        }
    }
}
