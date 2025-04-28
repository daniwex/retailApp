package com.adenikinju.productapplicationviewskotlin.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adenikinju.productapplicationviewskotlin.R
import com.adenikinju.productapplicationviewskotlin.adapters.FilterElementRecyclerViewAdapter
import com.adenikinju.productapplicationviewskotlin.data.RetailDatabase
import com.adenikinju.productapplicationviewskotlin.databinding.FragmentFilterBinding
import com.adenikinju.productapplicationviewskotlin.ui.viewmodel.RetailViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager


class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val retailViewModel: RetailViewModel by activityViewModels()
    private var filterElements = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        val btn = binding.btnShowResults

        retailViewModel.resetFiltering()
        retailViewModel.products.observe(viewLifecycleOwner) { products ->
            btn.text = "Show ${products.size} Results"
        }
        binding.rvGender.apply {
            adapter = FilterElementRecyclerViewAdapter(requireContext(), RetailDatabase.genderItems){ element ->
                filterElements = "Gender:$element"
                retailViewModel.filterElements(element)
            }
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvSize.apply {
            adapter = FilterElementRecyclerViewAdapter(requireContext(), RetailDatabase.sizeItems){
            }

            val flexboxLayoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent =
                    com.google.android.flexbox.JustifyContent.FLEX_START
            }

            layoutManager = flexboxLayoutManager
        }
        binding.rvColor.apply {
            adapter = FilterElementRecyclerViewAdapter(requireContext(), RetailDatabase.colorItems){}

            val flexboxLayoutManager = FlexboxLayoutManager(requireContext()).apply {
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
                justifyContent =
                    com.google.android.flexbox.JustifyContent.FLEX_START
            }
            layoutManager = flexboxLayoutManager
        }

        binding.btnShowResults.setOnClickListener {
            retailViewModel.setFilteredText(filterElements)
            navController.navigate(R.id.discover)

        }
    }
}