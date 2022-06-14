package com.gyamfimartins.dinnerbell

import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gyamfimartins.dinnerbell.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            findNavController(
                this@MainActivity,
                R.id.nav_host_fragment_content_main
            ).navigate(R.id.mealDetailsFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                navigateToProfile()
                true
            }
            R.id.action_category ->{
                navigateToCategory()
                return true
            }
            R.id.action_saved_meal ->{
                navigateToSavedMeals()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
}

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun navigateToProfile(){
        findNavController(
            this@MainActivity,
            R.id.nav_host_fragment_content_main
        ).navigate(R.id.profileFragment)
    }

    private fun navigateToCategory(){
        findNavController(
            this@MainActivity,
            R.id.nav_host_fragment_content_main
        ).navigate(R.id.categoryFragment)
    }

    private fun navigateToSavedMeals(){
        findNavController(
            this@MainActivity,
            R.id.nav_host_fragment_content_main
        ).navigate(R.id.savedMealFragment)
    }

}