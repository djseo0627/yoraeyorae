package com.yoraeyorae.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.data.RecyclerCategoryData
import com.yoraeyorae.databinding.RecyclerConvenienceCategoryBinding

class RecyclerCategoryAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerCategoryAdapter.CategoryViewHolder>() {

    var categoryData = mutableListOf<RecyclerCategoryData>()

    inner class CategoryViewHolder(private val binding : RecyclerConvenienceCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item : RecyclerCategoryData) {
                with(binding) {
                    data = item
                    executePendingBindings()
                }
            }
//        private val tvMenuName: TextView = itemView.findViewById(R.id.tv_convenience_category)
//        fun bind(item: RecyclerCategoryData) {
//            tvMenuName.text = item.menuName
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : RecyclerConvenienceCategoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.recycler_convenience_category,
            parent,
            false )
        return CategoryViewHolder(binding)
//        val view =
//            LayoutInflater.from(context)
//                .inflate(R.layout.recycler_convenience_category, parent, false)
//        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categoryData.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        categoryData[position].let{
            holder.bind(it)
        }
//        holder.bind(categoryDatas[position])
    }

    fun addAllCategory(data : List<RecyclerCategoryData>) {
        this.categoryData.addAll(data)
        notifyDataSetChanged()
    }

    fun addCategory(data : RecyclerCategoryData) {
        this.categoryData.add(data)
        notifyDataSetChanged()
    }

}
