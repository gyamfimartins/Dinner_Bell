package com.gyamfimartins.dinnerbell.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyamfimartins.dinnerbell.adapter.AreaAdapter
import com.gyamfimartins.dinnerbell.databinding.FragmentHomeBinding
import com.gyamfimartins.dinnerbell.viewmodel.AreaViewModel
import androidx.navigation.fragment.findNavController
import com.gyamfimartins.dinnerbell.R

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: AreaViewModel

    private val areaAdapter = AreaAdapter(arrayListOf()){
        navigateToMealsList(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(AreaViewModel::class.java)
        viewModel.refresh()

        binding.rvarea.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = areaAdapter
        }

        observeViewModel()

        return binding.root
    }

    fun observeViewModel() {
        viewModel.areaList.observe(requireActivity(), Observer {area ->
            area?.let {
                binding.rvarea.visibility = View.VISIBLE
                areaAdapter.updateAreaList(it)
            }
        })

        viewModel.areaLoadError.observe(requireActivity(), Observer { isError ->
            binding.listError.visibility = if(isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(requireActivity(), Observer { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.rvarea.visibility = View.GONE
                }
            }
        })
    }

    fun navigateToMealsList(country: String){
        val action = HomeFragmentDirections.actionHomeFragmentToMealListFragment(country,"Home")
        findNavController().navigate(action)
    }

}