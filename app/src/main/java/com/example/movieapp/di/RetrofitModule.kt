package com.example.movieapp.di

import com.example.movieapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
  
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(provideBaseUrl())
      .build()
  }
  
  @Provides
  fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
  ): OkHttpClient {
    
    return OkHttpClient()
      .newBuilder()
      .callTimeout(60, TimeUnit.MINUTES)
      .connectTimeout(60, TimeUnit.MINUTES)
      .readTimeout(60, TimeUnit.MINUTES)
      .writeTimeout(60, TimeUnit.MINUTES)
      .addInterceptor(loggingInterceptor)
      .build()
  }
  
  @Provides
  fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      setLevel(HttpLoggingInterceptor.Level.BODY)
    }
  }
  
  private fun provideBaseUrl() = BuildConfig.BASE_URL
}
