package ru.spiridonov.mimipizza.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.spiridonov.mimipizza.MiMiPizzaApp
import ru.spiridonov.mimipizza.R
import ru.spiridonov.mimipizza.databinding.ActivityMainBinding
import ru.spiridonov.mimipizza.domain.use_cases.menu_item.LoadDataUseCase
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as MiMiPizzaApp).component
    }

    @Inject
    lateinit var loadDataUseCase: LoadDataUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        loadDataUseCase.invoke()
    }
}