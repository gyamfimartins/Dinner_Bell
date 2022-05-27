package com.gyamfimartins.dinnerbell.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyamfimartins.dinnerbell.adapter.CategoryAdapter
import com.gyamfimartins.dinnerbell.databinding.FragmentCategoryBinding
import com.gyamfimartins.dinnerbell.viewmodel.CategoryViewModel

class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var viewModel: CategoryViewModel
    private val categoryAdapter = CategoryAdapter(arrayListOf()){
        navigateToMealsList(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        viewModel.refresh()

        binding.rvcategory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }

        observeViewModel()

        return binding.root
    }
    fun observeViewModel() {
        viewModel.categoryList.observe(requireActivity(), Observer {categories ->
            categories?.let {
                binding.rvcategory.visibility = View.VISIBLE
                categoryAdapter.updateCategoryList(it)
            }
        })

        viewModel.categoryLoadError.observe(requireActivity(), Observer { isError ->
            binding.listError.visibility = if(isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(requireActivity(), Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.rvcategory.visibility = View.GONE
                }
            }
        })
    }


    fun navigateToMealsList(category: String){
        val action = CategoryFragmentDirections.actionCategoryFragmentToMealListFragment(category,"category")
        findNavController().navigate(action)
    }

}