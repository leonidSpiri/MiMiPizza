package ru.spiridonov.mimipizza.presentation.adapters.cart_item

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import ru.spiridonov.mimipizza.R
import ru.spiridonov.mimipizza.data.database.menu.MenuAppDatabase
import ru.spiridonov.mimipizza.data.mapper.MenuMapper
import ru.spiridonov.mimipizza.data.network.ApiFactory
import ru.spiridonov.mimipizza.databinding.EachCartBinding
import ru.spiridonov.mimipizza.domain.entity.CartItem

class CartItemAdapter :
    ListAdapter<CartItem, CartItemViewHolder>(CartItemDiffCallback) {
    var onMenuItemClickListener: ((CartItem) -> Unit)? = null
    var onButtonMinusCartClickListener: ((CartItem) -> Unit)? = null
    var onButtonPlusCartClickListener: ((CartItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutID =
            when (viewType) {
                CART_ITEM -> R.layout.each_cart
                else -> throw RuntimeException("Unknown view type: $viewType")
            }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutID,
            parent,
            false
        )
        return CartItemViewHolder(binding)
    }

    override fun getItemViewType(position: Int) =
        CART_ITEM


    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            when (this) {
                is EachCartBinding -> {
                    cartItem = item

                    //very bad code, but i'm too hary to fix it. sorry

                    MenuAppDatabase.getInstance(root.context).menuListDao()
                        .getMenuItem(item.category, item.id).observeForever { dbModel ->
                            val menuItem = MenuMapper().mapDbModelToEntity(dbModel)
                            val sizePairArray = parseStringToArrayList(menuItem.size.toString())
                            txtName.text = menuItem.name

                            val priceList = parseStringToArrayList(menuItem.price)
                            var price = 0
                            if (priceList.size == 1) {
                                price = priceList[0].second.toInt()
                                txtSize.text = imageView.context.getString(
                                    R.string.weight,
                                    sizePairArray[0].second
                                )
                            } else
                                when (item.size) {
                                    "small" -> {
                                        price = priceList[0].second.toInt()
                                        txtSize.text = imageView.context.getString(
                                            R.string.size,
                                            sizePairArray[0].second
                                        )
                                    }
                                    "middle" -> {
                                        price = priceList[1].second.toInt()
                                        txtSize.text = imageView.context.getString(
                                            R.string.size,
                                            sizePairArray[1].second
                                        )
                                    }
                                    "big" -> {
                                        price = priceList[2].second.toInt()
                                        txtSize.text = imageView.context.getString(
                                            R.string.size,
                                            sizePairArray[2].second
                                        )
                                    }
                                }

                            btnSum.text =
                                btnSum.context.getString(
                                    R.string.sum,
                                    (price * item.count).toString()
                                )


                            imageView.background =
                                AppCompatResources.getDrawable(
                                    imageView.context,
                                    R.drawable.no_photo
                                )
                            val url = menuItem.imageUrl
                            val fullUrl = ApiFactory.BASE_URL + url
                            Picasso.get().load(fullUrl)
                                .into(imageView, object : com.squareup.picasso.Callback {
                                    override fun onSuccess() {
                                        imageView.background = null
                                    }

                                    override fun onError(e: Exception?) {
                                        Log.e("BindingAdapter", "Error loading image")
                                    }
                                })

                        }


                }
            }
            root.setOnClickListener {
                onMenuItemClickListener?.invoke(item)
            }

        }
    }

    private fun parseStringToArrayList(string: String): List<Pair<String, String>> {
        val list = mutableListOf<Pair<String, String>>()
        string.split(" ").forEach {
            if (it.contains(":")) {
                val pair = it.split(":")
                list.add(Pair(pair[0], pair[1]))
            } else if (it.isNotEmpty()) {
                list.add(Pair("one", it))
            }
        }
        return list
    }

    companion object {
        private const val CART_ITEM = 0
    }
}