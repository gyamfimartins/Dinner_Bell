package com.gyamfimartins.dinnerbell.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.R
import com.google.android.material.snackbar.Snackbar
import com.gyamfimartins.dinnerbell.adapter.IngredientAdapter
import com.gyamfimartins.dinnerbell.databinding.FragmentProfileBinding
import com.gyamfimartins.dinnerbell.viewmodel.IngredientViewModel


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: IngredientViewModel
    private val ingredientAdapter = IngredientAdapter(arrayListOf()){
        //viewMeaning(it)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)

        viewModel.refresh()

        observeViewModel()
        return binding.root
    }

    fun observeViewModel(){
        viewModel.ingredient.observe(requireActivity(), Observer { ingredientList ->
            ingredientList?.let {
                //binding.rvingredient.visibility = View.VISIBLE
                //ingredientAdapter.updateIngredientList(ingredientList)
                val mList = ingredientList.map { it.strIngredient }
                val ingredientAdapter = ArrayAdapter(
                    requireContext(), android.R.layout.simple_list_item_1, mList
                )
                binding.tvingredients.setAdapter(ingredientAdapter)
            }

        })
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            Log.i(ContentValues.TAG, "isLoading $isLoading")
            //binding.loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
        viewModel.errorMessage.observe(requireActivity(), Observer { errorMessage ->
            if (errorMessage == null) {
                //binding.listError.visibility = View.GONE
            } else {
               // binding.listError.visibility = View.VISIBLE
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }

}