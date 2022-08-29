package com.iuturakulov.uzbarchitecture_ar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.iuturakulov.uzbarchitecture_ar.R
import com.iuturakulov.uzbarchitecture_ar.databinding.ItemArchitectureBinding
import com.iuturakulov.uzbarchitecture_ar.model.ArchitectureInfo
import com.iuturakulov.uzbarchitecture_ar.ui.activities.DetailActivity

class ArchitectureAdapter
    : RecyclerView.Adapter<ArchitectureAdapter.PokemonViewHolder>() {

    private val items: MutableList<ArchitectureInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemArchitectureBinding>(
                inflater,
                R.layout.item_architecture,
                parent,
                false
            )
        return PokemonViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                DetailActivity.startActivity(binding.root, items[position])
            }
        }
    }

    fun setPokemonList(pokemonList: List<ArchitectureInfo>) {
        items.clear()
        items.addAll(pokemonList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.binding.apply {
            architecture = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class PokemonViewHolder(val binding: ItemArchitectureBinding) :
        RecyclerView.ViewHolder(binding.root)
}

