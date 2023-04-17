package com.iuturakulov.uzbarchitecture_ar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ArchCompactItemBinding
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.ui.activities.DetailActivity

class ArchitectureCompactAdapter : RecyclerView.Adapter<ArchitectureCompactAdapter.ArchViewHolder>() {

    private val items: MutableList<ArchitectureInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ArchCompactItemBinding>(
                inflater,
                R.layout.arch_compact_item,
                parent,
                false
            )
        return ArchViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                DetailActivity.startActivity(binding.root, items[position])
            }
        }
    }

    fun setArchList(archList: List<ArchitectureInfo>) {
        items.clear()
        items.addAll(archList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArchViewHolder, position: Int) {
        holder.binding.apply {
            architecture = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class ArchViewHolder(val binding: ArchCompactItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}

