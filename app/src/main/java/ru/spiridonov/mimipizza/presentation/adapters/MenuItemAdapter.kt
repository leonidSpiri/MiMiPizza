package ru.spiridonov.mimipizza.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import ru.spiridonov.mimipizza.R
import ru.spiridonov.mimipizza.databinding.EachMenuBinding
import ru.spiridonov.mimipizza.domain.entity.MenuItem

class MenuItemAdapter :
    ListAdapter<MenuItem, MenuItemViewHolder>(MenuItemDiffCallback) {
    var onMenuItemClickListener: ((MenuItem) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val layoutID =
            when (viewType) {
                MENU_ITEM -> R.layout.each_menu
                else -> throw RuntimeException("Unknown view type: $viewType")
            }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutID,
            parent,
            false
        )
        return MenuItemViewHolder(binding)
    }

    override fun getItemViewType(position: Int) =
        MENU_ITEM


    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            when (this) {
                is EachMenuBinding ->
                    menuItem = item
            }
            root.setOnClickListener {
                onMenuItemClickListener?.invoke(item)
            }

        }
    }

    companion object {
        private const val MENU_ITEM = 0
    }
}