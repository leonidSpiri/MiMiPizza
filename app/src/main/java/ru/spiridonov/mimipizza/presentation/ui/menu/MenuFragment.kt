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
import ru.spiridonov.mimipizza.domain.entity.CartItem
import ru.spiridonov.mimipizza.presentation.viewmodels.ViewModelFactory
import ru.spiridonov.mimipizza.presentation.adapters.menu_item.MenuItemAdapter
import ru.spiridonov.mimipizza.presentation.ui.details.DetailInfoActivity
import javax.inject.Inject

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding: FragmentMenuBinding
        get() = _binding ?: throw RuntimeException("FragmentMenuBinding is null")

    private lateinit var menuItemAdapter: MenuItemAdapter

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
        menuItemAdapter = MenuItemAdapter()
        observeViewModel()
        setupClickListener()
    }

    private fun observeViewModel() {
        viewModel.getMenuList().observe(viewLifecycleOwner) {
            binding.rvMenuList.adapter = menuItemAdapter
            menuItemAdapter.submitList(it)
        }
    }

    private fun setupClickListener() {
        menuItemAdapter.onMenuItemClickListener = {
            startActivity(DetailInfoActivity.newIntent(requireContext(), it))
        }
        menuItemAdapter.onButtonCartClickListener = {
            viewModel.addMenuItemToCart(
                CartItem(
                    id = it.id,
                    category = it.category,
                    size = "middle",
                    count = 1
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}