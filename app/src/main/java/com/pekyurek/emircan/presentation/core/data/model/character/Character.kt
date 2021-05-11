package com.pekyurek.emircan.presentation.core.data.model.character

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @Json(name = "created")
    val created: String? = null,
    @Json(name = "episode")
    val episode: List<String>? = null,
    @Json(name = "gender")
    val gender: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "origin")
    val origin: Origin? = null,
    @Json(name = "species")
    val species: String? = null,
    @Json(name = "status")
    val status: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "url")
    val url: String? = null,
    //Not Json
    val imageTransitionId: String = "image_transition_$id"
) : Parcelable {
}