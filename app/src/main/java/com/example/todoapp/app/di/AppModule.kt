package com.example.todoapp.app.di

import android.content.Context
import com.example.todoapp.utils.PreferenceUtil
import com.example.todoapp.utils.Session
import com.example.todoapp.utils.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePreferenceUtil(@ApplicationContext appContext: Context) = PreferenceUtil(appContext)

    @Singleton
    @Provides
    fun provideSession(preferenceUtil: PreferenceUtil) = Session(preferenceUtil)

    @Singleton
    @Provides
    fun stringsProvider(@ApplicationContext appContext: Context): StringProvider {
        return object : StringProvider {
            override fun provide(resourceID: Int) = appContext.getString(resourceID)
        }
    }
}