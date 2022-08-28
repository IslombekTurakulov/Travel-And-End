package com.iuturakulov.uzbarchitecture_ar.ui.adapter

import android.os.SystemClock
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ItemArchitectureBinding
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.ui.activities.DetailActivity
import com.skydoves.bindables.BindingListAdapter
import com.skydoves.bindables.binding

class ArchitectureAdapter :
    BindingListAdapter<Architecture, ArchitectureAdapter.ArchitectureViewHolder>(diffUtil) {

    private var onClickedAt = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchitectureViewHolder =
        parent.binding<ItemArchitectureBinding>(R.layout.item_architecture)
            .let(::ArchitectureViewHolder)

    override fun onBindViewHolder(holder: ArchitectureViewHolder, position: Int) =
        holder.bindPokemon(getItem(position))

    inner class ArchitectureViewHolder constructor(
        private val binding: ItemArchitectureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                val currentClickedAt = SystemClock.elapsedRealtime()
                if (currentClickedAt - onClickedAt > binding.transformationLayout.duration) {
                    DetailActivity.startActivity(binding.transformationLayout, getItem(position))
                    onClickedAt = currentClickedAt
                }
            }
        }

        fun bindPokemon(architecture: Architecture) {
            binding.component = architecture
            binding.executePendingBindings()
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<Architecture>() {

            override fun areItemsTheSame(oldItem: Architecture, newItem: Architecture): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Architecture, newItem: Architecture): Boolean =
                oldItem == newItem
        }
    }
}
