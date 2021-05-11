package com.pekyurek.emircan.presentation.core.data.model.episode

import com.pekyurek.emircan.presentation.core.data.model.character.Location
import com.pekyurek.emircan.presentation.core.data.model.character.Origin
import com.squareup.moshi.Json

data class Episode(
    @Json(name = "created")
    val created: String?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "location")
    val location: Location?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin")
    val origin: Origin?,
    @Json(name = "species")
    val species: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "characters")
    val characters: List<String>?,
    @Json(name = "episode")
    val episode: String?
)