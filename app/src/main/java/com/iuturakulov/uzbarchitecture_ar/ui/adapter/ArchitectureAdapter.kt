package com.iuturakulov.uzbarchitecture_ar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ItemArchitectureBinding
import com.iuturakulov.uzbarchitecture_ar.model.Architecture
import com.iuturakulov.uzbarchitecture_ar.ui.activities.DetailActivity

class ArchitectureAdapter : RecyclerView.Adapter<ArchitectureAdapter.ArchitectureViewHolder>() {

    private val items: MutableList<Architecture> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchitectureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemArchitectureBinding>(
                inflater,
                R.layout.item_architecture,
                parent,
                false
            )
        return ArchitectureViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                DetailActivity.startActivity(binding.root, items[position])
            }
        }
    }

    fun setArchitectDataList(archList: List<Architecture>) {
        items.clear()
        items.addAll(archList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArchitectureViewHolder, position: Int) {
        holder.binding.apply {
            archcomponent = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class ArchitectureViewHolder(val binding: ItemArchitectureBinding) :
        RecyclerView.ViewHolder(binding.root)
}
