package com.yoraeyorae.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.data.RecyclerCategoryData
import com.yoraeyorae.adapters.recycler.data.RecyclerConvenienceData
import com.yoraeyorae.databinding.RecyclerConvenienceBinding

class RecyclerConvenienceAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerConvenienceAdapter.ViewHolder>() {
    private var convenienceData = mutableListOf<RecyclerConvenienceData>()
    private var isClose: Boolean = false

    inner class ViewHolder(private val binding : RecyclerConvenienceBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RecyclerConvenienceData) {

            val categoryAdapter = RecyclerCategoryAdapter(context)
            val categoryData = mutableListOf<RecyclerCategoryData>()
            categoryData.add(RecyclerCategoryData("음료"))
            categoryData.add(RecyclerCategoryData("라면"))
            categoryData.add(RecyclerCategoryData("과자"))
            categoryData.add(RecyclerCategoryData("아이스크림"))
            categoryAdapter.addAllCategory(categoryData)
            binding.rvConvenienceCategory.adapter = categoryAdapter

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
                    if (!isClose) {
                        binding.rvConvenienceCategory.visibility = View.VISIBLE
                        isClose = true
                    } else {
                        binding.rvConvenienceCategory.visibility = View.GONE
                        isClose = false
                    }
                }
            }

            with(binding) {
                data = item
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int = convenienceData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        convenienceData[position].let{
            holder.bind(it)
        }
//        holder.bind(convenienceData[position])
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : RecyclerConvenienceBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.recycler_convenience,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    fun addAllConvenience(datas : List<RecyclerConvenienceData>){
        this.convenienceData.addAll(datas)
        notifyDataSetChanged()
    }

    fun addItem(data : RecyclerConvenienceData) {
        this.convenienceData.add(data)
        notifyDataSetChanged()
    }

//    inner class ViewHolder(view: View) :
//        RecyclerView.ViewHolder(view) {
//        private val txtName: TextView = itemView.findViewById(R.id.tv_convenience_name)
//        private val txtDistance: TextView = itemView.findViewById(R.id.tv_convenience_distance)
//        private val imgThumbnail: ImageView = itemView.findViewById(R.id.iv_convenience_thumbnail)
//
//        private var isClose: Boolean = false
//        private var categoryDatas = mutableListOf<RecyclerCategoryData>()
//
//        private lateinit var categoryCategoryAdapter : RecyclerCategoryAdapter
//
//
//        fun bind(item: RecyclerConvenienceData) {
//            txtName.text = item.name
//            txtDistance.text = item.distance
//            Glide.with(itemView).load(item.img).into(imgThumbnail) //썸네일 이미지 넣기
//
//            val rvCategory = itemView.findViewById<RecyclerView>(R.id.rv_convenience_category)
//            categoryCategoryAdapter = RecyclerCategoryAdapter(context)
//            rvCategory.adapter = categoryCategoryAdapter
////            rvCategory.layoutManager = LinearLayoutManager(context)
//            rvCategory.setHasFixedSize(true)
//
//            categoryDatas.apply {
//                add(RecyclerCategoryData("메뉴1"))
//                add(RecyclerCategoryData("메뉴2"))
//                add(RecyclerCategoryData("메뉴3"))
//                add(RecyclerCategoryData("메뉴4"))
//                add(RecyclerCategoryData("메뉴5"))
//                add(RecyclerCategoryData("메뉴6"))
//            }
//            categoryCategoryAdapter.categoryDatas = categoryDatas
//            categoryCategoryAdapter.notifyDataSetChanged()
//
//
//            val pos = adapterPosition
//            if (pos != RecyclerView.NO_POSITION) {
//                itemView.setOnClickListener {
//                    if(!isClose) {
//                        rvCategory.visibility = View.VISIBLE
//                        isClose = true
//                    }
//                    else {
//                        rvCategory.visibility = View.GONE
//                        isClose = false
//                    }
//                }
//            }
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view =
//            LayoutInflater.from(context).inflate(R.layout.recycler_convenience, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = convenienceDatas.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(convenienceDatas[position])
//    }
}
