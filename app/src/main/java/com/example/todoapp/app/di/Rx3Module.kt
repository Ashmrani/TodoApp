package com.example.todoapp.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Rx3Module {

    @Provides
    @Singleton
    @UiThreadScheduler
    fun provideUIScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @IoThreadScheduler
    fun provideSubscribeOnScheduler(): Scheduler {
        return Schedulers.io()
    }
}