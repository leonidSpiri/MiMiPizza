package ru.spiridonov.mimipizza.presentation.ui.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.databinding.FragmentCartBinding
import ru.spiridonov.mimipizza.presentation.ViewModelFactory
import javax.inject.Inject

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val component by lazy {
        (requireActivity().application as MiMiPizzaApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CartViewModel

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}