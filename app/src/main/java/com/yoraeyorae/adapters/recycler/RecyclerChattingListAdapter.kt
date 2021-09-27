package com.yoraeyorae.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.data.RecyclerChattingListData
import com.yoraeyorae.databinding.RecyclerChattingListBinding
import com.yoraeyorae.databinding.RecyclerConvenienceCategoryBinding

class RecyclerChattingListAdapter(/*private val context: Context*/private val navController: NavController) :
    RecyclerView.Adapter<RecyclerChattingListAdapter.ViewHolder>() {

    var listData = mutableListOf<RecyclerChattingListData>()

    inner class ViewHolder(private val bind: RecyclerChattingListBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun bind(item: RecyclerChattingListData) {

            val pos = adapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                itemView.setOnClickListener {
//                    val action = FragmentListDirections.actionFragmentListToFragmentChatting(currentUser, binding.tvUserName.text.toString(), listData[pos].roomid)
                    navController.navigate(R.id.action_fragmentChatting_to_fragmentChattingRoom)
                }
            }

            with(bind) {
                data = item
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: RecyclerChattingListAdapter.ViewHolder, position: Int) {
        listData[position].let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerChattingListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : RecyclerChattingListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.recycler_chatting_list,
            parent,
            false )
        return ViewHolder(binding)
    }

    fun addItem(data : RecyclerChattingListData) {
        this.listData.add(data)
        notifyDataSetChanged()
    }

    fun addAllItem(data : List<RecyclerChattingListData>) {
        this.listData.addAll(data)
        notifyDataSetChanged()
    }

}