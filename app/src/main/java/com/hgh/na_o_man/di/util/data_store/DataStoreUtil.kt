package com.hgh.na_o_man.di.util.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "na_o_man")

class DataStoreUtil @Inject constructor(
    private val context: Context
) {

    companion object {
        private val KEY_ACCESS_TOKEN = stringPreferencesKey(name = "na_o_man_access")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey(name = "na_o_man_refresh")
        private val KEY_AUTO_LOGIN = booleanPreferencesKey(name = "na_o_man_auto_login")
    }

    suspend fun setAccessToken(token: String) {
        context.dataStore.edit { pref ->
            pref[KEY_ACCESS_TOKEN] = token
        }
    }

    suspend fun getAccessToken(): String? {
        return context.dataStore.data.map { it[KEY_ACCESS_TOKEN] }.firstOrNull()
    }

    suspend fun setRefreshToken(refreshToken: String) {
        context.dataStore.edit { pref ->
            pref[KEY_REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun getRefreshToken(): String? {
        return context.dataStore.data.map { it[KEY_REFRESH_TOKEN] }.firstOrNull()
    }

    suspend fun setAutoLogin(isAutoLogin: Boolean) {
        context.dataStore.edit { pref ->
            pref[KEY_AUTO_LOGIN] = isAutoLogin
        }
    }

    suspend fun getAutoLogin(): Boolean {
        return context.dataStore.data.map { it[KEY_AUTO_LOGIN] }.firstOrNull() ?: false
    }

    suspend fun clearDataStore() {
        context.dataStore.edit {
            it.clear()
        }
    }
}