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
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getMenuList().observe(viewLifecycleOwner) {
            val menuItemAdapter = MenuItemAdapter()
            binding.rvMenuList.adapter = menuItemAdapter
            menuItemAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}