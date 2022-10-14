package ru.spiridonov.mimipizza.presentation.ui.details

import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.R
import ru.spiridonov.mimipizza.databinding.ActivityDetailInfoBinding
import ru.spiridonov.mimipizza.domain.entity.MenuItem

class DetailInfoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailInfoBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as MiMiPizzaApp).component
    }
    private val menuItem by lazy {
        when {
            SDK_INT >= 33 -> intent.getParcelableExtra(MENU_ITEM, MenuItem::class.java)
                ?: throw IllegalArgumentException("No menu item")
            else -> @Suppress("DEPRECATION")
            intent.getParcelableExtra(MENU_ITEM)
                ?: throw IllegalArgumentException("No menu item")
        }
    }
    private val sizePairArray by lazy {
        parseStringToArrayList(menuItem.size.toString())
    }
    private var chooseSize = "middle"
    private var choosePrice = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.menuItem = menuItem

        setupPriceAndSizeInfo(parseStringToArrayList(menuItem.price))
        setupAddToCartButton()
    }

    private fun setupAddToCartButton() {
        binding.btnAddToCart.setOnClickListener {
            Log.d(
                "DetailInfoActivity", "add to cart: " +
                        "category = ${menuItem.category}, id = ${menuItem.id}, " +
                        "size = $chooseSize, price = $choosePrice"
            )
        }
    }

    private fun setupPriceAndSizeInfo(list: List<Pair<String, String>>) {
        if (list.size == 1) {
            binding.secondChip.setChipBackgroundColorResource(R.color.good)
            binding.secondChip.visibility = View.VISIBLE
            binding.secondChip.setOnCheckedChangeListener { _, _ ->
                binding.btnAddToCart.text = getString(R.string.add_to_cart, list[0].second)
                binding.txtSize.text = getString(R.string.weight, sizePairArray[0].second)
                choosePrice = list[0].second
            }
        } else
            list.forEach { pair ->
                when (pair.first) {
                    "small" -> {
                        binding.firstChip.visibility = View.VISIBLE
                        binding.firstChip.setOnCheckedChangeListener { _, _ ->
                            binding.firstChip.setChipBackgroundColorResource(R.color.good)
                            binding.secondChip.setChipBackgroundColorResource(R.color.bad)
                            binding.thirdChip.setChipBackgroundColorResource(R.color.bad)
                            binding.btnAddToCart.text =
                                getString(R.string.add_to_cart, list[0].second)
                            binding.txtSize.text = getString(R.string.size, sizePairArray[0].second)
                            chooseSize = "small"
                            choosePrice = list[0].second
                        }
                    }
                    "middle" -> {
                        binding.secondChip.visibility = View.VISIBLE
                        binding.secondChip.setOnCheckedChangeListener { _, _ ->
                            binding.firstChip.setChipBackgroundColorResource(R.color.bad)
                            binding.secondChip.setChipBackgroundColorResource(R.color.good)
                            binding.thirdChip.setChipBackgroundColorResource(R.color.bad)
                            binding.btnAddToCart.text =
                                getString(R.string.add_to_cart, list[1].second)
                            binding.txtSize.text = getString(R.string.size, sizePairArray[1].second)
                            chooseSize = "middle"
                            choosePrice = list[1].second
                        }
                    }
                    "big" -> {
                        binding.thirdChip.visibility = View.VISIBLE
                        binding.thirdChip.setOnCheckedChangeListener { _, _ ->
                            binding.firstChip.setChipBackgroundColorResource(R.color.bad)
                            binding.secondChip.setChipBackgroundColorResource(R.color.bad)
                            binding.thirdChip.setChipBackgroundColorResource(R.color.good)
                            binding.btnAddToCart.text =
                                getString(R.string.add_to_cart, list[2].second)
                            binding.txtSize.text = getString(R.string.size, sizePairArray[2].second)
                            chooseSize = "big"
                            choosePrice = list[2].second
                        }
                    }
                }
            }
        binding.secondChip.isChecked = true
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
        private const val MENU_ITEM = "menu_item"
        fun newIntent(context: Context, menuItem: MenuItem): Intent {
            val intent = Intent(context, DetailInfoActivity::class.java)
            intent.putExtra(MENU_ITEM, menuItem)
            return intent
        }
    }
}