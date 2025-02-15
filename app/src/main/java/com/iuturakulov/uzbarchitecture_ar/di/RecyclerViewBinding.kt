package com.iuturakulov.uzbarchitecture_ar.di

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.ui.adapter.ArchitectureCompactAdapter
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
        view.adapter = adapter.apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    @JvmStatic
    @BindingAdapter("adapterArchList")
    fun bindAdapterArchList(view: RecyclerView, archList: List<ArchitectureInfo>?) {
        archList.whatIfNotNullOrEmpty { itemList ->
            view.adapter.whatIfNotNullAs<ArchitectureCompactAdapter> { adapter ->
                adapter.setArchList(itemList)
            }
        }
    }
}
