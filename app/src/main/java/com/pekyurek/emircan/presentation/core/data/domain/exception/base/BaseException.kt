package com.pekyurek.emircan.presentation.core.data.domain.exception.base

import androidx.annotation.StringRes
import com.pekyurek.emircan.R


open class BaseException(override val message: String) : Throwable() {

    @StringRes
    open val titleResId = R.string.exception_title_generic_error
}