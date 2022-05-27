package com.gyamfimartins.dinnerbell.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gyamfimartins.dinnerbell.adapter.MealAdapter
import com.gyamfimartins.dinnerbell.data.Meal
import com.gyamfimartins.dinnerbell.data.MealList
import com.gyamfimartins.dinnerbell.databinding.FragmentMealListBinding
import com.gyamfimartins.dinnerbell.viewmodel.MealViewModel


class MealListFragment : Fragment() {

    private lateinit var binding: FragmentMealListBinding
    private lateinit var viewModel: MealViewModel
    private val mealAdapter = MealAdapter(arrayListOf()){
        navigateToMealsDetails(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealListBinding.inflate(inflater, container, false)
        val area = (arguments?.getString("area"))
        val screen = (arguments?.getString("screen"))

        viewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        viewModel.refresh(area!!,screen!!)

        binding.rvmeal.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mealAdapter
        }

       observeViewModel()
        return binding.root
    }

    fun navigateToMealsDetails(mealid: String){
        val action = MealListFragmentDirections.actionMealListFragmentToMealDetailsFragment(mealid, "other")
        findNavController().navigate(action)
    }

    fun observeViewModel(){
        viewModel.mealList.observe(requireActivity(), Observer { mealList ->
            mealList?.let {
                binding.rvmeal.visibility = View.VISIBLE
                mealAdapter.updateMealList(mealList)
            }

        })
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            Log.i(TAG, "isLoading $isLoading")
            binding.loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        viewModel.errorMessage.observe(requireActivity(), Observer { errorMessage ->
            if (errorMessage == null) {
                binding.listError.visibility = View.GONE
            } else {
                binding.listError.visibility = View.VISIBLE
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

}