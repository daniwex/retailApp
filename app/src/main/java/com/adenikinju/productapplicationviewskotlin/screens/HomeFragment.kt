package com.adenikinju.productapplicationviewskotlin.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adenikinju.productapplicationviewskotlin.R
import com.adenikinju.productapplicationviewskotlin.adapters.HomeCategoryRecyclerViewAdapter
import com.adenikinju.productapplicationviewskotlin.adapters.ProductsRecyclerViewAdapter
import com.adenikinju.productapplicationviewskotlin.data.ProductCategory
import com.adenikinju.productapplicationviewskotlin.databinding.FragmentHomeBinding
import com.adenikinju.productapplicationviewskotlin.recyclerview.GridSpacingItemDecoration
import com.adenikinju.productapplicationviewskotlin.ui.viewmodel.RetailViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val retailViewModel: RetailViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        retailViewModel.productCategory.observe(viewLifecycleOwner) { productCategories ->
            val categories = productCategories ?: listOf(
                ProductCategory("Shirts", 4),
                ProductCategory("Outerwears", 4)
            )
            binding.rvHomeProductCategories.apply {
                adapter = HomeCategoryRecyclerViewAdapter(categories, requireContext())
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }

        retailViewModel.recommendedProducts.observe(viewLifecycleOwner) { recommendedProduct ->

            binding.rvRecommendedProducts.apply {
                adapter = ProductsRecyclerViewAdapter(requireContext(), recommendedProduct)
                layoutManager = GridLayoutManager(requireContext(), 2)
                addItemDecoration(GridSpacingItemDecoration(2, 25, false))
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
