package ru.spiridonov.mimipizza.presentation.ui.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.databinding.FragmentMenuBinding
import ru.spiridonov.mimipizza.domain.entity.MenuItem
import ru.spiridonov.mimipizza.presentation.ViewModelFactory
import ru.spiridonov.mimipizza.presentation.adapters.MenuItemAdapter
import javax.inject.Inject

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MenuViewModel

    private val component by lazy {
        (requireActivity().application as MiMiPizzaApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        viewModel = ViewModelProvider(this, viewModelFactory)[MenuViewModel::class.java]
        val menuItemAdapter = MenuItemAdapter()
        binding.rvMenuList.adapter = menuItemAdapter
        val menuItems = mutableListOf<MenuItem>()
        menuItems.add(
            MenuItem(
                id = 0,
                name = "Пицца Маргарита",
                description = "Описание пиццы 0",
                price = 235,
                category = "Пицца",
                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                countOfAvailable = 10,
            )
        )

        menuItemAdapter.submitList(menuItems)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}