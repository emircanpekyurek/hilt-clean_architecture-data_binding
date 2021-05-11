package com.pekyurek.emircan.presentation.core.data.model.character

import com.pekyurek.emircan.presentation.core.data.model.base.RAMBaseRequest

data class CharacterDetailRequest(
    val characterId: Int
) : RAMBaseRequest()