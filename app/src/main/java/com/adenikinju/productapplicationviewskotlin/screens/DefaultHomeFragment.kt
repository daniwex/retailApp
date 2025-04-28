package com.adenikinju.productapplicationviewskotlin.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.adenikinju.productapplicationviewskotlin.R
import com.adenikinju.productapplicationviewskotlin.adapters.ProductsRecyclerViewAdapter
import com.adenikinju.productapplicationviewskotlin.databinding.FragmentDefaultHomeBinding
import com.adenikinju.productapplicationviewskotlin.recyclerview.GridSpacingItemDecoration
import com.adenikinju.productapplicationviewskotlin.ui.viewmodel.RetailViewModel

class DefaultHomeFragment : Fragment() {

    private var _binding: FragmentDefaultHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private val retailViewModel by activityViewModels<RetailViewModel>()
    private lateinit var productsAdapter: ProductsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDefaultHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        super.onViewCreated(view, savedInstanceState)


        productsAdapter = ProductsRecyclerViewAdapter(requireContext(), emptyList())
        binding.rvProducts.apply {
            adapter = productsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(GridSpacingItemDecoration(2, 25, false))
        }

        retailViewModel.products.observe(viewLifecycleOwner) { products ->
            productsAdapter.updateProducts(products)
        }

        binding.svSearchDiscover.svSearchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                retailViewModel.filterElements(newText ?: "")
                return true
            }
        })

        retailViewModel.filteredProductsText.observe(viewLifecycleOwner) { element ->
            Log.d("TAG", element)
            if(element.isNotBlank()){
                Log.d("TAG", element)
                binding.svSearchDiscover.svSearchItem.setQuery(element, false)
            }
        }

        binding.btnFilter.setOnClickListener {
            navController.navigate(R.id.filterFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
