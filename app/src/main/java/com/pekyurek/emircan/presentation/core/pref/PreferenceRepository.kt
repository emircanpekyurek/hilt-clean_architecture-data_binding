package com.pekyurek.emircan.presentation.core.pref


interface PreferenceRepository {

    fun getFavoriteCharacterIds(): Set<String>

    fun addFavoriteCharacterId(id: String)

    fun characterIsFavorite(id: String): Boolean

    fun removeFavoriteCharacter(id: String)
}