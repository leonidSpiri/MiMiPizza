package ru.spiridonov.mimipizza.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.R
import ru.spiridonov.mimipizza.databinding.ActivityMainBinding
import ru.spiridonov.mimipizza.domain.use_cases.menu_item.GetMenuListUseCase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as MiMiPizzaApp).component
    }
    @Inject
    lateinit var getMenuListUseCase: GetMenuListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        CoroutineScope(Dispatchers.Main).launch {
            val array = getMenuListUseCase.invoke()
            Log.d("TAG", "onCreate: $array")
        }
    }
}