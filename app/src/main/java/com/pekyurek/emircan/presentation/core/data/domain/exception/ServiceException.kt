package com.pekyurek.emircan.presentation.core.data.domain.exception

import com.pekyurek.emircan.R
import com.pekyurek.emircan.presentation.core.data.domain.exception.base.BaseException

open class ServiceException(message: String) : BaseException(message) {

    override val titleResId: Int = R.string.exception_title_service_error
}