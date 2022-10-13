package ru.spiridonov.mimipizza.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.spiridonov.mimipizza.domain.entity.MenuItem

object MenuItemDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
    override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem) = oldItem == newItem
}