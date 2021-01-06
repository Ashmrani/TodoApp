package com.example.todoapp.auth.di

import com.example.data.auth.AuthApi
import com.example.data.auth.AuthImpl
import com.example.domain.auth.AuthRepository
import com.example.domain.auth.usecase.LoginUseCase
import com.example.domain.auth.usecase.OtpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object LoginModule {

    @ActivityScoped
    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @ActivityScoped
    @Provides
    fun provideAuthRepository(service: AuthApi): AuthRepository =
        AuthImpl(service)


    @ActivityScoped
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository) = LoginUseCase(authRepository)


    @ActivityScoped
    @Provides
    fun provideOtpUseCase(authRepository: AuthRepository) = OtpUseCase(authRepository)

}