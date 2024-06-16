package com.core.network.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun setToken(token: String?) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token ?: ""
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    companion object {
        val TOKEN_KEY = stringPreferencesKey("token")
    }
}