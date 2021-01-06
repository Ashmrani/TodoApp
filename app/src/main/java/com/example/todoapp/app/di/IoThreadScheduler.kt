package com.example.todoapp.app.di

import javax.inject.Qualifier

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class IoThreadScheduler