package com.jephtecolin.varoflix.di

import android.content.Context
import com.jephtecolin.varoflix.data.local.MovieDao
import com.jephtecolin.varoflix.data.local.VaroflixRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): VaroflixRoomDatabase {
        return VaroflixRoomDatabase.getDatabase(context)
    }

    @Provides
    fun provideMovieDao(varoMovieRoomDatabase: VaroflixRoomDatabase): MovieDao {
        return varoMovieRoomDatabase. movieDao()
    }
}