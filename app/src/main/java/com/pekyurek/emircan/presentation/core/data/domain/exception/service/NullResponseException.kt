package com.pekyurek.emircan.presentation.core.data.domain.exception.service

import android.content.Context
import android.content.res.Resources
import com.pekyurek.emircan.R
import com.pekyurek.emircan.presentation.core.data.domain.exception.ServiceException

class NullResponseException(context: Context) :
    ServiceException(
        context.getString(R.string.exception_service_null_response)
    )