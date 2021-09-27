package com.yoraeyorae.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.adapters.recycler.data.RecyclerMissionListData
import com.yoraeyorae.databinding.RecyclerMissionListBinding

class RecyclerMissionListAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerMissionListAdapter.MissionListViewHolder>() {
    var listData = mutableListOf<RecyclerMissionListData>()

    fun addAllItems(data : List<RecyclerMissionListData>) {
        this.listData.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data : RecyclerMissionListData) {
        this.listData.add(data)
        notifyDataSetChanged()
    }

    inner class MissionListViewHolder(private val binding : RecyclerMissionListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : RecyclerMissionListData) {
            with(binding) {
                data = item
                executePendingBindings()
            }
        }
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: MissionListViewHolder, position: Int) {
        listData[position].let{
            holder.bind(it)
        }
//        holder.bind(listData[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerMissionListBinding.inflate(inflater, parent, false)
        return MissionListViewHolder(binding)
    }
}