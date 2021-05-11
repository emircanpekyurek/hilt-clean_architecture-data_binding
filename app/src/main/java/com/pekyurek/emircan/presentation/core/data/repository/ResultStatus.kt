package com.pekyurek.emircan.presentation.core.data.repository

import com.pekyurek.emircan.presentation.core.data.domain.exception.base.BaseException


sealed class ResultStatus<out Response> {

    data class Success<Data>(val data: Data) : ResultStatus<Data>()

    data class Exception(val exception: BaseException) : ResultStatus<Nothing>()

    data class Loading(val show: Boolean) : ResultStatus<Nothing>()

}