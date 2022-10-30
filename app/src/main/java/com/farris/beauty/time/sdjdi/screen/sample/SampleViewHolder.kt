package com.farris.beauty.time.sdjdi.screen.sample

import androidx.recyclerview.widget.RecyclerView
import com.farris.beauty.time.sdjdi.databinding.ItemSampleBinding

class SampleViewHolder(private val binding: ItemSampleBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SampleItem) {
        binding.townShipTextView.text = item.townShipName
        binding.elementTextView.text = item.element
        binding.startTextView.text = item.start
        binding.endTextView.text = item.end
        binding.valueTextView.text = item.valueText
    }
}