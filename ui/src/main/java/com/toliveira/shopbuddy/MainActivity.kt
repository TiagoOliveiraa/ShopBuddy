package com.toliveira.shopbuddy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.toliveira.shopbuddy.databinding.ActivityMainBinding
import com.toliveira.shopbuddy.list.fragment.ListFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
//                R.id.navigation_shopping -> replaceFragment(ShoppingFragment())
//                R.id.navigation_spending -> replaceFragment(SpendingFragment())
                R.id.navigation_list -> replaceFragment(ListFragment())
            }
            false
        }

        binding.bottomNavigation.selectedItemId = R.id.navigation_list


    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit()

        return true
    }

}