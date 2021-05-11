package com.pekyurek.emircan.presentation.core.data.model.character

import android.os.Parcelable
import com.pekyurek.emircan.presentation.core.data.model.base.RAMBaseResponse
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val characters: List<Character>?
) : RAMBaseResponse(), Parcelable