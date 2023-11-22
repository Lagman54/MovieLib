package com.example.finalproject.di

import com.example.finalproject.presentation.image.GlideLoadImageImpl
import com.example.finalproject.presentation.image.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {

    @Singleton
    @Provides
    fun provideImageLoader() : ImageLoader = GlideLoadImageImpl()
}