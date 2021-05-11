package com.pekyurek.emircan.presentation.ui.main.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pekyurek.emircan.presentation.core.data.domain.usecase.character.CharacterUseCase
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.character.CharacterRequest
import com.pekyurek.emircan.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) : BaseViewModel() {

    val pagingCharacterList = MutableLiveData<PagingData<Character>>()

    private val characterRequest = CharacterRequest()

    init {
        loadPagingCharacterList()
    }

    fun onCharacterNameChanged(text: CharSequence) {
        characterRequest.name = text.toString()
        loadPagingCharacterList()
    }

    fun onCharacterStatusChanged(text: CharSequence) {
        characterRequest.status = text.toString()
        loadPagingCharacterList()
    }

    private fun loadPagingCharacterList() = viewModelScope.launch {
        characterUseCase.pagingFlow(characterRequest).cachedIn(viewModelScope).distinctUntilChanged()
            .collectLatest {
                PagingData
                pagingCharacterList.postValue(it)
            }
    }
}