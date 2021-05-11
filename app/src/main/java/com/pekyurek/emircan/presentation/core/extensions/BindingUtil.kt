package com.pekyurek.emircan.presentation.core.extensions

import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pekyurek.emircan.presentation.core.pref.RAMPreferences

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("loadCircleImageFromUrl")
fun loadCircleImageFromUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).circleCrop().into(imageView)
}

@BindingAdapter("setHasFixedSize")
fun setHasFixedSize(recyclerView: RecyclerView, hasFixedSize: Boolean) {
    recyclerView.setHasFixedSize(hasFixedSize)
}

@BindingAdapter("addOrRemoveCharacterFavorite")
fun addOrRemoveCharacterFavorite(checkBox: CheckBox, id: Int) {
    checkBox.isChecked =
        RAMPreferences.getInstance(checkBox.context).characterIsFavorite(id.toString())
    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
        RAMPreferences.getInstance(checkBox.context).run {
            if (!buttonView.isPressed) return@run
            if (isChecked) {
                addFavoriteCharacterId(id.toString())
            } else {
                removeFavoriteCharacter(id.toString())
            }
        }
    }
}