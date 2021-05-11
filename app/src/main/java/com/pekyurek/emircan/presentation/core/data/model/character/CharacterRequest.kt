package com.pekyurek.emircan.presentation.core.data.model.character

import com.pekyurek.emircan.presentation.core.data.model.base.RAMBaseRequest

data class CharacterRequest(
    var pageNumber: Int = 1
) : RAMBaseRequest() {

    var name: String? = null
        get() = field?.ifBlank { null }

    var status: String? = null
        get() = field?.ifBlank { null }

}
