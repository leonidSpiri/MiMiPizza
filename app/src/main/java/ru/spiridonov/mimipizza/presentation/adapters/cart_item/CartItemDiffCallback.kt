package ru.spiridonov.mimipizza.presentation.adapters.cart_item

import androidx.recyclerview.widget.DiffUtil
import ru.spiridonov.mimipizza.domain.entity.CartItem

object CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem) =
        (oldItem.id == newItem.id) && (oldItem.category == newItem.category)

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem == newItem
}