package com.gyamfimartins.dinnerbell.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gyamfimartins.dinnerbell.adapter.SavedMealAdapter
import com.gyamfimartins.dinnerbell.data.SavedMeal
import com.gyamfimartins.dinnerbell.databinding.FragmentSavedMealBinding
import com.gyamfimartins.dinnerbell.viewmodel.SavedMealViewModel


class SavedMealFragment : Fragment() {
    private lateinit var binding: FragmentSavedMealBinding
    private lateinit var savedMealViewModel: SavedMealViewModel
    private val savedMealAdapter = SavedMealAdapter(arrayListOf(),
        { navigateToMealsDetails(it)},
        {deleteMeal(it)}
        )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedMealBinding.inflate(inflater, container, false)

        binding.rvmeal.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = savedMealAdapter
        }
        savedMealViewModel = ViewModelProvider(this).get(SavedMealViewModel::class.java)
        savedMealViewModel.getAllData.observe(viewLifecycleOwner, Observer { mealList ->
            savedMealAdapter.updateMealList(mealList)
        })


        return binding.root
    }


    fun navigateToMealsDetails(mealid: String){
        val action = MealListFragmentDirections.actionMealListFragmentToMealDetailsFragment(mealid, "other")
        findNavController().navigate(action)
    }

    fun deleteMeal(meal: SavedMeal){
      savedMealViewModel.deleteMeal(meal)
    }

}