package com.yoraeyorae.adapters

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yoraeyorae.R
import java.text.DecimalFormat

object BindingAdapters {

    @BindingAdapter("imageFromUrl")
    @JvmStatic
    fun bindImageFromUrl(view: ImageView, imageUrl : String?) {
        if( !imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .error(R.drawable.sample)
                .into(view)
        }
    }


    @BindingAdapter("etBindComma")
    @JvmStatic
    fun EditText.etBindComm(amount : Int) {
        val formatter = DecimalFormat("###,###")
        this.setText(formatter.format(amount))
    }

    @BindingAdapter("tvBindComma")
    @JvmStatic
    fun TextView.tvBindComm(amount : Int) {
        val formatter = DecimalFormat("###,###")
        this.text = formatter.format(amount)
    }



//    @BindingAdapter("android:visibility")
//    @JvmStatic
//    fun setVisibility(view: View, value: Boolean) {
//        view.visibility = if (value) View.VISIBLE else View.GONE
//    }
}