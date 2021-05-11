package com.pekyurek.emircan.presentation.core.pref

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager


class RAMPreferences(private val context: Context) : PreferenceRepository {

    private val KEY_CHARACTER_LIST = "character_list"

    private val pref by lazy { PreferenceManager.getDefaultSharedPreferences(context) }

    override fun getFavoriteCharacterIds(): Set<String> = pref.getStringSet(
        KEY_CHARACTER_LIST,
        emptySet()
    ).orEmpty()

    override fun addFavoriteCharacterId(id: String) =
        pref.edit {
            putStringSet(
                KEY_CHARACTER_LIST,
                getFavoriteCharacterIds().toMutableSet().apply {
                    add(id)
                })
        }


    override fun characterIsFavorite(id: String): Boolean =
        getFavoriteCharacterIds().contains(id)


    override fun removeFavoriteCharacter(id: String) {
        pref.edit {
            putStringSet(
                KEY_CHARACTER_LIST,
                getFavoriteCharacterIds().toMutableSet().apply { remove(id) })
        }
    }

    companion object {
        @Volatile
        private var instance: RAMPreferences? = null

        fun getInstance(context: Context): RAMPreferences {

            return instance ?: synchronized(this) {
                if (instance == null) {
                    instance = RAMPreferences(context)
                }
                instance!!
            }
        }
    }

}