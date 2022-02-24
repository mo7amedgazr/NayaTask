package com.app.nayatechnotask.presentation.base

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.app.nayatechnotask.R
import com.app.nayatechnotask.domain.entity.ListItem
import com.app.nayatechnotask.utils.gone
import com.app.nayatechnotask.utils.visible
import com.bumptech.glide.Glide

object CustomBindings {

    @SuppressLint("SetTextI18n")
    @BindingAdapter(value = ["listItem", "currency"], requireAll = false)
    @JvmStatic
    fun isDiscountAvailable(textView: AppCompatTextView, listItem: ListItem?, currency: String?) {
        listItem?.originalPrice?.let {
            textView.visible()
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            textView.text = "$it $currency"
        } ?: run {
            textView.gone()
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImageUrl(appCompatImageView: AppCompatImageView, url: String?) {
        url?.let {
            Glide
                .with(appCompatImageView.context)
                .load(url)
                .into(appCompatImageView)
        }
    }

    @BindingAdapter("isWishlist")
    @JvmStatic
    fun isWishlist(appCompatImageView: AppCompatImageView, saved: Boolean) {
        if (saved) {
            appCompatImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    appCompatImageView.context,
                    R.mipmap.saved
                )
            )
        } else {
            appCompatImageView.setImageDrawable(
                ContextCompat.getDrawable(
                    appCompatImageView.context,
                    R.mipmap.save
                )
            )
        }
    }
}