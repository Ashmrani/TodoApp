package com.example.todoapp.di

import com.example.todoapp.auth.login.LoginActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun bindActivity(activity: LoginActivity): MainContract.View

    @Binds
    abstract fun bindPresenter(impl: MainPresenter): MainContract.Presenter

}

@InstallIn(ActivityComponent::class)
@Module
abstract class WelcomeMessageDataSourceModule {

    @Binds
    abstract fun bindDataSource(
        impl: GetWelcomeMessageDataSourceImpl
    ): GetWelcomeMessageDataSource

}

@InstallIn(ActivityComponent::class)
@Module
object MainActivityModule {

    @Provides
    fun bindActivity(activity: Activity): MainActivity {
        return activity as MainActivity
    }
}