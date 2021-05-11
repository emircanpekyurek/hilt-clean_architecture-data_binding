package com.pekyurek.emircan.presentation.core.data.repository

import android.content.Context
import com.pekyurek.emircan.presentation.core.data.domain.exception.ServiceException
import com.pekyurek.emircan.presentation.core.data.domain.exception.base.BaseException
import com.pekyurek.emircan.presentation.core.data.domain.exception.service.FailResponseException
import com.pekyurek.emircan.presentation.core.data.domain.exception.service.NullResponseException
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class RAMRepositoryImplTest {

    private val remoteDataSource = mockk<RAMRemoteDataSource>()

    private lateinit var repositoryImpl: RAMRepositoryImpl

    private val context: Context = mockk(relaxed = true)

    @Before
    fun setUp() {
        repositoryImpl = RAMRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `getCharacter() success case`() {
        runBlocking {
            //given
            val pageNo = 1
            val responseModel = CharacterResponse(null, listOf())

            val responseFlow = flow {
                emit(ResultStatus.Loading(show = true))
                emit(ResultStatus.Success(responseModel))
                emit(ResultStatus.Loading(show = false))
            }
            coEvery {
                remoteDataSource.getCharacter(pageNo, null, null)
            } returns responseFlow

            //when
            val result = repositoryImpl.getCharacter(pageNo, null, null).toList()

            //then
            assert(result[0] is ResultStatus.Loading)
            assert((result[0] as ResultStatus.Loading).show)
            assert((result[1] is ResultStatus.Success))
            assertEquals((result[1] as ResultStatus.Success).data, responseModel)
            assert(result[2] is ResultStatus.Loading)
            assert((result[2] as ResultStatus.Loading).show.not())
        }
    }

    @Test
    fun `getCharacter() NullResponseException case`() {
        characterFailCase(NullResponseException(context))
    }

    @Test
    fun `getCharacter() ServiceException case`() {
        characterFailCase(ServiceException("mock error"))
    }

    @Test
    fun `getCharacter() FailResponseException case`() {
        characterFailCase(FailResponseException(context, 404))
    }


    private fun characterFailCase(exception: BaseException) {
        runBlocking {
            //given
            val pageNo = 1

            val responseFlow = flow {
                emit(ResultStatus.Loading(show = true))
                emit(ResultStatus.Exception(exception))
                emit(ResultStatus.Loading(show = false))
            }
            coEvery {
                remoteDataSource.getCharacter(pageNo, null, null)
            } returns responseFlow

            //when
            val result = repositoryImpl.getCharacter(pageNo, null, null).toList()

            //then
            assert(result[0] is ResultStatus.Loading)
            assert((result[0] as ResultStatus.Loading).show)
            assert(result[1] is ResultStatus.Exception)
            assert(result[1] is ResultStatus.Exception)
            assertEquals((result[1] as ResultStatus.Exception).exception.message, exception.message)

            assertEquals((result[1] as ResultStatus.Exception).exception, exception) //or

            assert(result[2] is ResultStatus.Loading)
            assert((result[2] as ResultStatus.Loading).show.not())
        }
    }

}
