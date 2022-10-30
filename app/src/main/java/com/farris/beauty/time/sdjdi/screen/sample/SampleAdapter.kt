package com.farris.beauty.time.sdjdi.screen.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.farris.beauty.time.sdjdi.R
import com.farris.beauty.time.sdjdi.databinding.ItemSampleBinding

class SampleAdapter : ListAdapter<SampleItem, SampleViewHolder>(object : DiffUtil.ItemCallback<SampleItem>() {
    override fun areItemsTheSame(oldItem: SampleItem, newItem: SampleItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: SampleItem, newItem: SampleItem): Boolean {
        return false
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(
            ItemSampleBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false))
        )
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}