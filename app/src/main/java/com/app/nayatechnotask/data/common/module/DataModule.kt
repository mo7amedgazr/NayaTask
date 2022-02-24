package com.app.nayatechnotask.data.common.module

import android.content.Context
import com.app.nayatechnotask.data.repository.ListItemsRepositoryImpl
import com.app.nayatechnotask.domain.ListItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindListRepository(listItemsRepository: ListItemsRepositoryImpl): ListItemsRepository


}