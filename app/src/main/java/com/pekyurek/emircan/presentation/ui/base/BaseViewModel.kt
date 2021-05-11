package com.pekyurek.emircan.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pekyurek.emircan.presentation.core.data.domain.exception.base.BaseException
import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class BaseViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val showErrorPopup = MutableLiveData<BaseException>()

    fun showLoading(visibility: Boolean) = loading.postValue(visibility)

    suspend fun <T> request(
        flow: Flow<ResultStatus<T>>,
        onSuccess: ((data: T) -> Unit)? = null,
        onError: ((t: BaseException) -> Unit)? = null,
        forceLoadingHidden: Boolean = false,
        errorPopupCanVisible: Boolean = true
    ) {
        flow.collect { result ->
            when (result) {
                is ResultStatus.Loading -> showLoading(result.show && forceLoadingHidden.not())
                is ResultStatus.Success -> onSuccess?.invoke(result.data)
                is ResultStatus.Exception -> {
                    if (errorPopupCanVisible) {
                        showErrorPopup.postValue(result.exception)
                    }
                    onError?.invoke(result.exception)
                }
            }
        }
    }
}