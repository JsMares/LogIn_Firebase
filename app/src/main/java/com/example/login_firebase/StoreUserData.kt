package com.example.login_firebase

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserData(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("storeUser")
        val STORE_USER = stringPreferencesKey("store_user")
    }

    val getUserData: Flow<String?> = context.dataStore.data
        .map {  preference ->
            preference[STORE_USER]
        }

    suspend fun saveUser(idUser: String) {
        context.dataStore.edit {  preference ->
            preference[STORE_USER] = idUser
        }
    }
}