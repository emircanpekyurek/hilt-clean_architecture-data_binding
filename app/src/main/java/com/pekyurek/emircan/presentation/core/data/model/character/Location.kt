package com.pekyurek.emircan.presentation.core.data.model.character

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
) : Parcelable