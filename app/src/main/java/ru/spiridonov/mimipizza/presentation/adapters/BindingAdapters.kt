package ru.spiridonov.mimipizza.presentation.adapters

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import ru.spiridonov.mimipizza.R
import ru.spiridonov.mimipizza.data.network.ApiFactory
import java.lang.Exception

@BindingAdapter("getMinPrice")
fun bindGetMinPrice(textView: TextView, price: String) {
    var text = ""
    for (i in price.indices) {
        if (price[i].isDigit())
            text += price[i]
        else if (text.isNotEmpty()) break
    }
    textView.text = textView.context.getString(R.string.min_price, text)
}

@BindingAdapter("setLogo")
fun bindSetLogo(imageView: ImageView, url: String) {
    imageView.background = imageView.context.getDrawable(R.drawable.pizza)
    val fullUrl = ApiFactory.BASE_URL + url
    Picasso.get().load(fullUrl).into(imageView, object : com.squareup.picasso.Callback {
        override fun onSuccess() {
            imageView.background = null
        }

        override fun onError(e: Exception?) {
            Log.e("BindingAdapter", "Error loading image")
        }
    })
}