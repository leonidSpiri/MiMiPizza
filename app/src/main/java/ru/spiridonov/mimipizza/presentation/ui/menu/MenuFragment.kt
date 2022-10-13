package ru.spiridonov.mimipizza.presentation.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.spiridonov.mimipizza.databinding.FragmentMenuBinding
import ru.spiridonov.mimipizza.domain.entity.MenuItem
import ru.spiridonov.mimipizza.presentation.adapters.MenuItemAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MenuViewModel
    var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MenuViewModel::class.java]
        val menuItemAdapter = MenuItemAdapter()
        binding.rvMenuList.adapter = menuItemAdapter
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Маргарита",
                description = "Описание пиццы ${counter++}",
                price = 235,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Маргарита 2",
                description = "Описание пиццы ${counter++}",
                price = 1003450,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Маргарита 3",
                description = "Описание пиццы ${counter++}",
                price = 3245,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Ветчина и грибы",
                description = "Описание пиццы ${counter++}",
                price = 104500,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Ветчина и грибы 2",
                description = "Описание пиццы ${counter++}",
                price = 10700,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Баварские колбаски",
                description = "Описание пиццы ${counter++}",
                price = 1000,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItems.add(
            MenuItem(
                id = counter,
                name = "Пицца Баварские колбаски 2",
                description = "Описание пиццы ${counter++}",
                price = 100,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
            )
        )
        menuItemAdapter.submitList(menuItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}