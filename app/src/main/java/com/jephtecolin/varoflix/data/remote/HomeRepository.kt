package com.jephtecolin.varoflix.data.remote

import javax.inject.Inject

//class HomeRepository @Inject constructor(
//private val tmdbService: TMDBService
//) {
//    companion object {
//        private const val NETWORK_PAGE_SIZE = 20
//    }
//
//    private val _movieList = MutableStateFlow<Result<List<TmdbMovie>>>(Result.Loading)
//    val movieList: StateFlow<Result<List<TmdbMovie>>> = _movieList
//
//    private var currentQuery: String? = null
//    private var currentResult: Flow<PagingData<TmdbMovie>>? = null
//
//    fun searchMovies(query: String): Flow<PagingData<TmdbMovie>> {
//        val lastResult = currentResult
//        if (query == currentQuery && lastResult != null) {
//            return lastResult
//        }
//        currentQuery = query
//        val newResult = Pager(
//            config = PagingConfig(
//                pageSize = NETWORK_PAGE_SIZE,
//                enablePlaceholders = false
//            ),
//            pagingSourceFactory = { TmdbMoviePagingSource(tmdbService, query) }
//        ).flow
//        currentResult = newResult
//        return newResult
//    }
//
//    suspend fun loadMovies(page: Int = 1) {
//        try {
//            val tmdbMovieResponse = tmdbService.getPopularMovies(page = page)
//            val movieList = tmdbMovieResponse.results
//            _movieList.value = Result.Success(movieList)
//        } catch (e: Exception) {
//            _movieList.value = Result.Error(e)
//        }
//    }
//}
