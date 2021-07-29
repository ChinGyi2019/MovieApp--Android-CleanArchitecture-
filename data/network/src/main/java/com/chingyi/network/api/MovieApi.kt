package com.chingyi.network.api


import com.chingyi.network.BuildConfig
import com.chingyi.network.response.GenreListResponse
import com.chingyi.network.response.UpComingMovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface MovieApi {

    @GET("movie/upcoming?api_key=${BuildConfig.APP_SECRET}") // @Query("api_key") apiKey: String?
    suspend fun fetchUpComingMovies():
            Response<UpComingMovieListResponse>

    @GET("movie/popular?api_key=${BuildConfig.APP_SECRET}")
    suspend fun fetchPopularMovies(
        @Query("page") page : Int)
    :Response<UpComingMovieListResponse>

    @GET("tv/popular?api_key=${BuildConfig.APP_SECRET}")
    suspend fun fetchPopularSeries(
        @Query("page") page : Int)
            :Response<UpComingMovieListResponse>

    @GET("genre/movie/list?api_key=${BuildConfig.APP_SECRET}")
    suspend fun fetchGenreList()
    :Response<GenreListResponse>

}