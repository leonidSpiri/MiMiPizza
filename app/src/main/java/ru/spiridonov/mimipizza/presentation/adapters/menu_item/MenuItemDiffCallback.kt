package ru.spiridonov.mimipizza.presentation.adapters.menu_item

import androidx.recyclerview.widget.DiffUtil
import ru.spiridonov.mimipizza.domain.entity.MenuItem

object MenuItemDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
    override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem) =
        (oldItem.id == newItem.id) && (oldItem.category == newItem.category)

    override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem) = oldItem == newItem
}