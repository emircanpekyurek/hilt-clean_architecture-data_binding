package com.pekyurek.emircan.presentation.ui.detail.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pekyurek.emircan.presentation.core.data.domain.usecase.detail.EpisodeDetailUseCase
import com.pekyurek.emircan.presentation.core.data.model.character.Character
import com.pekyurek.emircan.presentation.core.data.model.episode.Episode
import com.pekyurek.emircan.presentation.core.data.model.episode.EpisodeDetailRequest
import com.pekyurek.emircan.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val episodeDetailUseCase: EpisodeDetailUseCase
) : BaseViewModel() {

    val lastEpisode = MutableLiveData<Episode>()

    fun getLastEpisode(character: Character) =
        character.episode?.lastOrNull()?.split("/")?.lastOrNull()?.let { lastEpisodeId ->
            viewModelScope.launch {
                request(
                    episodeDetailUseCase.execute(EpisodeDetailRequest(lastEpisodeId)),
                    errorPopupCanVisible = false,
                    onSuccess = { episode ->
                        lastEpisode.postValue(episode)
                    }
                )
            }
        }

}