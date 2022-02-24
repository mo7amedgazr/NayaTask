package com.app.nayatechnotask.data.common.module

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


//private val Context.dataStore by preferencesDataStore(
//    name = ""
//)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

//    @Provides
//    fun provideSharedPref(@ApplicationContext context: Context) : DataStoreManager {
//        return DataStoreManager(context)
//    }
}