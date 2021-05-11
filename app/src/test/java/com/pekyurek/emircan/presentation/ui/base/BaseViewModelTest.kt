package com.pekyurek.emircan.presentation.ui.base

import com.pekyurek.emircan.presentation.core.data.domain.exception.base.BaseException
import com.pekyurek.emircan.presentation.core.data.repository.ResultStatus
import org.junit.Assert.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test


class BaseViewModelTest {

    class MockViewModel : BaseViewModel()
    private val viewModel = MockViewModel()

    private val successData = "success_data"
    private val successFlow = flow {
        emit(ResultStatus.Loading(true))
        emit(ResultStatus.Success(successData))
        emit(ResultStatus.Loading(false))
    }


    private val testException = BaseException("test_exception")
    private val errorFlow = flow<ResultStatus<String>> {
        emit(ResultStatus.Loading(true))
        emit(ResultStatus.Exception(testException))
        emit(ResultStatus.Loading(false))
    }

    @Test
    fun successResponse() {
        runBlocking {
            viewModel.request(successFlow, onSuccess = {
                assertEquals(it, successData)
            }, onError = {
                assert(false)
            })
        }
    }


    @Test
    fun failResponse() {
        runBlocking {
            viewModel.request(errorFlow, onSuccess = {
                assert(false)
            }, onError = {
                assertEquals(it, testException)
            })
        }
    }

}