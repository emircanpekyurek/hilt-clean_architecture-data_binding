package com.pekyurek.emircan.presentation.core.data.domain.exception.service

import android.content.Context
import com.pekyurek.emircan.R
import com.pekyurek.emircan.presentation.core.data.domain.exception.ServiceException

class FailResponseException(context: Context, val responseStatus: Int) :
    ServiceException(
        context.getString(
            R.string.exception_service_fail_response_with_status,
            responseStatus
        )
    )