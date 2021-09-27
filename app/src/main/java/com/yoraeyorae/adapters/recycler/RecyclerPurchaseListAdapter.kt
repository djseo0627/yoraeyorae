package com.yoraeyorae.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.data.RecyclerPurchaseListData
import com.yoraeyorae.databinding.RecyclerConveniencePurchaseListBinding

class RecyclerPurchaseListAdapter (private val context: Context) :
    RecyclerView.Adapter<RecyclerPurchaseListAdapter.ViewHolder>() {
    var purchaselistDatas = mutableListOf<RecyclerPurchaseListData>()

    inner class ViewHolder(private val binding : RecyclerConveniencePurchaseListBinding) :
     RecyclerView.ViewHolder(binding.root){
        fun bind(item : RecyclerPurchaseListData) {
            with(binding){
                data = item
            }
        }
    }

    override fun getItemCount(): Int = purchaselistDatas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        purchaselistDatas[position].let{
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding : RecyclerConveniencePurchaseListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.recycler_convenience_purchase_list,
            parent,
            false
        )
        return ViewHolder(binding)
//        val view =
//            LayoutInflater.from(context).inflate(R.layout.recycler_convenience_purchase_list, parent, false)
//        return ViewHolder(view)
    }

//    inner class ViewHolder(view: View) :
//    RecyclerView.ViewHolder(view)  {
//        private val tvName: TextView = itemView.findViewById(R.id.tv_purchase_name)
//        private val tvAmount: TextView = itemView.findViewById(R.id.tv_purchase_amount)
//        private val imgThumbnail: ImageView = itemView.findViewById(R.id.iv_purchase_thumbnail)
//
//        fun bind(item: RecyclerPurchaseListData) {
//            tvName.text = item.purchaseName
//            tvAmount.text = item.purchaseAmount
//            Glide.with(itemView).load(item.purchaseImg).into(imgThumbnail) //썸네일 이미지 넣기
//
//        }
//    }





}