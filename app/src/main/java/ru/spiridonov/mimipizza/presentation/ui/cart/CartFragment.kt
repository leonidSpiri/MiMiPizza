package ru.spiridonov.mimipizza.presentation.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.databinding.FragmentCartBinding
import ru.spiridonov.mimipizza.presentation.adapters.cart_item.CartItemAdapter
import ru.spiridonov.mimipizza.presentation.viewmodels.ViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding: FragmentCartBinding
        get() = _binding ?: throw RuntimeException("FragmentCartBinding is null")

    private val component by lazy {
        (requireActivity().application as MiMiPizzaApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CartViewModel

    private lateinit var cartItemAdapter: CartItemAdapter

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
        cartItemAdapter = CartItemAdapter()
        observeViewModel()
        setupSwipeListener(binding.rvCartList)
    }

    private fun observeViewModel() {
        viewModel.getCartList().observe(viewLifecycleOwner) {
            binding.rvCartList.adapter = cartItemAdapter
            cartItemAdapter.submitList(it)
        }
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback: ItemTouchHelper.SimpleCallback
        callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = cartItemAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteCartItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}