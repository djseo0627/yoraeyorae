package com.yoraeyorae.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.R
import com.yoraeyorae.adapters.recycler.data.RecyclerAccountData
import com.yoraeyorae.databinding.RecyclerAccountBinding
import okhttp3.internal.notify

class RecyclerAccountAdapter() : RecyclerView.Adapter<RecyclerAccountAdapter.ViewHolder>() {

    private var accountData = mutableListOf<RecyclerAccountData>()

    inner class ViewHolder(private val bind : RecyclerAccountBinding) :
    RecyclerView.ViewHolder(bind.root) {
        fun bind(item : RecyclerAccountData) {

            if(item.img != null) {
                bind.ivBackground.setImageBitmap(item.img)
            }

            with(bind){
                data = item
                executePendingBindings()
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAccountAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bind : RecyclerAccountBinding = DataBindingUtil.inflate(
            inflater, R.layout.recycler_account, parent, false
        )

        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: RecyclerAccountAdapter.ViewHolder, position: Int) {
        accountData[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = accountData.size

    fun addItem(data : RecyclerAccountData) {
        accountData.add(data)
        notifyDataSetChanged()
    }

    fun addAllItem(data : List<RecyclerAccountData>) {
        accountData.addAll(data)
        notifyDataSetChanged()
    }


}