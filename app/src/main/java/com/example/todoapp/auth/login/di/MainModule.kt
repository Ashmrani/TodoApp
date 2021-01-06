package com.example.todoapp.di

import android.app.Activity
import com.example.data.auth.AuthImpl
import com.example.domain.auth.AuthRepository
import com.example.todoapp.auth.login.LoginActivity
import com.example.todoapp.auth.login.LoginContract
import com.example.todoapp.auth.login.LoginPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class LoginModule {

    @Binds
    abstract fun bindActivity(activity: LoginActivity): LoginContract.View

    @Binds
    abstract fun bindPresenter(impl: LoginPresenter): LoginContract.Presenter

}

@InstallIn(ActivityComponent::class)
@Module
abstract class LoginDataSourceModule {

    @Binds
    abstract fun bindDataSource(
        impl: AuthImpl
    ): AuthRepository

}

@InstallIn(ActivityComponent::class)
@Module
object LoginActivityModule {

    @Provides
    fun bindActivity(activity: Activity): LoginActivity {
        return activity as LoginActivity
    }
}