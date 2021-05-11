package com.pekyurek.emircan.presentation.core.data.model.episode

import com.pekyurek.emircan.presentation.core.data.model.base.RAMBaseRequest

data class EpisodeDetailRequest(
    val episodeId: String
) : RAMBaseRequest()