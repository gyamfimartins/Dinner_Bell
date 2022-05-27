package com.gyamfimartins.dinnerbell.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewMeaning(it)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)

        viewModel.refresh()
        binding.rvingredient.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ingredientAdapter
        }

        observeViewModel()
        return binding.root
    }

    fun viewMeaning(strDescription: String){
        val snackbar: Snackbar = Snackbar.make(binding.root, strDescription, Snackbar.LENGTH_LONG)

        val snackbarView = snackbar.view
        val snackTextView = snackbarView.findViewById<View>(R.id.snackbar_text) as TextView

        snackTextView.maxLines = 100
        snackbar.show()
    }

    fun observeViewModel(){
        viewModel.ingredient.observe(requireActivity(), Observer { ingredientList ->
            ingredientList?.let {
                binding.rvingredient.visibility = View.VISIBLE
                ingredientAdapter.updateIngredientList(ingredientList)
            }

        })
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            Log.i(ContentValues.TAG, "isLoading $isLoading")
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