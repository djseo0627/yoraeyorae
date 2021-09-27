package com.yoraeyorae.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.data.RecyclerChattingData
import com.yoraeyorae.databinding.RecyclerChattingBinding
import com.yoraeyorae.databinding.RecyclerChattingListBinding

class RecyclerChattingAdapter(private val currentUser : String) :
RecyclerView.Adapter<RecyclerChattingAdapter.ViewHolder>() {

    private var chatData = mutableListOf<RecyclerChattingData>()

    inner class ViewHolder(private val bind : RecyclerChattingBinding) :
    RecyclerView.ViewHolder(bind.root) {
        fun bind(item : RecyclerChattingData) {

            if( item.user == currentUser) {
                bind.cvOtherChatting.visibility = View.GONE
                if(item.type != "text") {
                    bind.ivMyImage.visibility = View.VISIBLE
                    bind.tvMyText.visibility = View.GONE
                    bind.ivMyImage.setImageBitmap(item.img)
//                    Glide.with(itemView).load(item.img).into(bind.ivMyImage) //썸네일 이미지 넣기
                }
//                binding.cvMyChatting.setCardBackgroundColor(Color.parseColor("#FFF176"))
            }
            else {
                bind.cvMyChatting.visibility = View.GONE
                if(item.type != "text") {
                    bind.ivOtherImage.visibility = View.VISIBLE
                    bind.tvOtherText.visibility = View.GONE
                    bind.ivOtherImage.setImageBitmap(item.img)
//                    Glide.with(itemView).load(item.img).into(bind.ivMyImage) //썸네일 이미지 넣기

                }
            }

            with(bind) {
                data = item
                executePendingBindings()
            }
        }

    }

    override fun getItemCount(): Int = chatData.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : RecyclerChattingBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.recycler_chatting,
            parent,
            false )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        chatData[position].let {
            holder.bind(it)
        }
    }

    fun addItem(data : RecyclerChattingData) {
        chatData.add(data)
        notifyDataSetChanged()
    }

    fun addAllItem(data : List<RecyclerChattingData>) {
        chatData.addAll(data)
        notifyDataSetChanged()
    }


}