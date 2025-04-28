package com.adenikinju.productapplicationviewskotlin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adenikinju.productapplicationviewskotlin.R
import com.adenikinju.productapplicationviewskotlin.databinding.FragmentFilterBinding
import com.adenikinju.productapplicationviewskotlin.databinding.SimpleLayoutBinding

class FilterElementRecyclerViewAdapter(
    private val context: Context,
    private val filteringElement: List<String>,
    private val onItemClicked: (String) -> Unit
): RecyclerView.Adapter<FilterElementRecyclerViewAdapter.FilterElementViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterElementViewHolder {
        val binding = SimpleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterElementViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FilterElementViewHolder,
        position: Int
    ) {
        holder.bind(filteringElement[position], position == selectedPosition)

    }

    override fun getItemCount() = filteringElement.size

    inner class FilterElementViewHolder(itemView: SimpleLayoutBinding): RecyclerView.ViewHolder(itemView.root){
        fun bind(itemText: String, isSelected: Boolean) {
            val item: TextView = itemView.findViewById(R.id.tvFilterName)
            item.text = itemText
            // Change button appearance based on selection
            if (isSelected) {
               item.setBackgroundColor(context.getColor(R.color.green_550))
                item.setTextColor(context.getColor(R.color.green_800))
            } else {
                item.setBackgroundColor(context.getColor(R.color.white))
                item.setTextColor(context.getColor(R.color.black))
            }

            // Set click listener
            item.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition

                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                onItemClicked(itemText)
            }
        }
    }
}