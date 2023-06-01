package com.kryptopass.asteroidradar.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kryptopass.asteroidradar.R
import com.kryptopass.asteroidradar.databinding.ItemAsteroidBinding
import com.kryptopass.asteroidradar.domain.Asteroid

class AsteroidRecyclerAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Asteroid, AsteroidRecyclerAdapter.ItemViewHolder>(DiffCallback) {

    class ItemViewHolder(private var binding: ItemAsteroidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid) {
            binding.item = asteroid
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ItemAsteroidBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.item_asteroid, parent, false)

                return ItemViewHolder(binding)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }

    class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }
}