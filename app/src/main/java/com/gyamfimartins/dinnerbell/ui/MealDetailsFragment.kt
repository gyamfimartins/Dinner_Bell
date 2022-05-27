package com.gyamfimartins.dinnerbell.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gyamfimartins.dinnerbell.R
import com.gyamfimartins.dinnerbell.databinding.FragmentMealDetailsBinding
import com.gyamfimartins.dinnerbell.viewmodel.MealDetailViewModel
import com.gyamfimartins.dinnerbell.viewmodel.MealViewModel
import com.retrofitcoroutines.example.utils.loadImage


class MealDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMealDetailsBinding
    private lateinit var viewModel: MealDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealDetailsBinding.inflate(inflater, container, false)
        val mealid = (arguments?.getString("mealid"))?: ""
        val screen = (arguments?.getString("screen"))?: "Random"

        viewModel = ViewModelProvider(this).get(MealDetailViewModel::class.java)
        viewModel.refresh(mealid,screen)
        observeViewModel()
        return binding.root
    }

    fun observeViewModel(){
        viewModel.mealDetailList.observe(requireActivity(), Observer { mealList ->
            mealList?.let {
                binding.ivMealImage.loadImage(mealList.get(0).strMealThumb)
                binding.tvinstruction.text = mealList.get(0).strInstructions
                binding.tvvideoLink.text = mealList.get(0).strYoutube?: ""
                binding.tvmealname.text = mealList.get(0).strMeal
            }

        })
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            Log.i(ContentValues.TAG, "isLoading $isLoading")
        })
        viewModel.errorMessage.observe(requireActivity(), Observer { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }


}