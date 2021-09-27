package com.yoraeyorae.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yoraeyorae.adapters.recycler.data.RecyclerSelectedItemData
import com.yoraeyorae.R
import com.yoraeyorae.databinding.RecyclerSelectedItemBinding

class RecyclerSelectedItemAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerSelectedItemAdapter.ViewHolder>()/*, View.OnClickListener*/ {
    var selectedItemData = mutableListOf<RecyclerSelectedItemData>()

    fun addAllItems(data: List<RecyclerSelectedItemData>) {
        this.selectedItemData.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(data: RecyclerSelectedItemData) {
        this.selectedItemData.add(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: RecyclerSelectedItemBinding) :
        RecyclerView.ViewHolder(binding.root)/*, View.OnClickListener*/ {
        fun bind(item: RecyclerSelectedItemData) {
            binding.btnItemMinus.setOnClickListener { onClickMinus(binding) }
            binding.btnItemPlus.setOnClickListener { onClickPlus(binding) }

            binding.btnItemDelete.setOnClickListener(View.OnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    selectedItemData.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                    notifyItemRangeChanged(adapterPosition, selectedItemData.size)
                }


                //context에 문제가 있어 보류
//               val alertDialog = AlertDialog.Builder(context)
//                   .setTitle("삭제")
//                   .setMessage("삭제하시겠습니까?")
//                   .setPositiveButton( "예", DialogInterface.OnClickListener{ dialog, which ->
//                       selectedItemData.removeAt(adapterPosition)
//                       notifyDataSetChanged()
//                   })
//                   .setNegativeButton("아니오", DialogInterface.OnClickListener { dialog, which ->
//                       dialog.cancel()
//                   })
//
//                alertDialog.show()
            })



            with(binding) {
                data = item
                executePendingBindings()
            }

        }

        private fun onClickMinus(binding: RecyclerSelectedItemBinding) {
            var curCount: Int = Integer.parseInt(binding.etItemCount.text.toString())
            curCount--
            binding.etItemCount.setText(curCount.toString())
            notifyDataSetChanged()
        }

        private fun onClickPlus(binding: RecyclerSelectedItemBinding) {
            var curCount: Int = Integer.parseInt(binding.etItemCount.text.toString())
            curCount++
            binding.etItemCount.setText(curCount.toString())
            notifyDataSetChanged()
        }

        private fun onClickDelete(binding: RecyclerSelectedItemBinding) {
            val pos = adapterPosition
            if( pos != RecyclerView.NO_POSITION) {
                selectedItemData.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerSelectedItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.recycler_selected_item,
            parent,
            false
        )

        binding.etItemCount.setText("1")
        binding.tvItemMoney.text = "3,000"

//        binding.btnItemDelete.setOnClickListener { onClickDelete(binding) }

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = selectedItemData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        selectedItemData[position].let {
            holder.bind(it)
        }
    }

}