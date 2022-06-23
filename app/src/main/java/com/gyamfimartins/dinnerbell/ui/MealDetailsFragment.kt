package com.gyamfimartins.dinnerbell.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gyamfimartins.dinnerbell.data.Allergy
import com.gyamfimartins.dinnerbell.data.MealDetail
import com.gyamfimartins.dinnerbell.data.SavedMeal
import com.gyamfimartins.dinnerbell.databinding.FragmentMealDetailsBinding
import com.gyamfimartins.dinnerbell.viewmodel.AllergyViewModel
import com.gyamfimartins.dinnerbell.viewmodel.MealDetailViewModel
import com.gyamfimartins.dinnerbell.viewmodel.SavedMealViewModel
import com.retrofitcoroutines.example.utils.loadImage


class MealDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMealDetailsBinding
    private lateinit var viewModel: MealDetailViewModel
    private var mealUrl: String = ""
    private lateinit var savedMealViewModel: SavedMealViewModel
    private lateinit var allergyViewModel: AllergyViewModel
    private lateinit var mList: List<Allergy>

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
        savedMealViewModel = ViewModelProvider(this).get(SavedMealViewModel::class.java)
        allergyViewModel = ViewModelProvider(this).get(AllergyViewModel::class.java)
        allergyViewModel.getAllAllergy.observe(viewLifecycleOwner,{ allergyList ->
            mList = allergyList
        })

        binding.btnsave.setOnClickListener {
            val newData = SavedMeal(0,mealid,binding.tvmealname.text.toString(),
                mealUrl)
            savedMealViewModel.addMeal(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    fun observeViewModel(){
        viewModel.mealDetailList.observe(requireActivity(), Observer { mealList ->
            mealList?.let {
                binding.ivMealImage.loadImage(mealList.get(0).strMealThumb)
                binding.tvinstruction.text = mealList.get(0).strInstructions
                binding.tvvideoLink.text = mealList.get(0).strYoutube?: ""
                binding.tvmealname.text = mealList.get(0).strMeal
                mealUrl = mealList.get(0).strMealThumb
                binding.tvallergy.isVisible = isAllergic(mealList.get(0))
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

    fun isAllergic(mealDetail: MealDetail): Boolean{
        return mList.any{it.strIngredient == mealDetail.strIngredient1 ||
                it.strIngredient == mealDetail.strIngredient2 ||
                it.strIngredient == mealDetail.strIngredient3 ||
                it.strIngredient == mealDetail.strIngredient4 ||
                it.strIngredient == mealDetail.strIngredient5 ||
                it.strIngredient == mealDetail.strIngredient6 ||
                it.strIngredient == mealDetail.strIngredient7 ||
                it.strIngredient == mealDetail.strIngredient8 ||
                it.strIngredient == mealDetail.strIngredient9 ||
                it.strIngredient == mealDetail.strIngredient10 ||
                it.strIngredient == mealDetail.strIngredient11 ||
                it.strIngredient == mealDetail.strIngredient12 ||
                it.strIngredient == mealDetail.strIngredient13 ||
                it.strIngredient == mealDetail.strIngredient14 ||
                it.strIngredient == mealDetail.strIngredient15
        }
    }


}