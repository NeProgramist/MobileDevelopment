package ua.kpi.comsys.ip8408.feature_filmlist.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ua.kpi.comsys.ip8408.feature_filmlist.data.model.FilmDetailedResponse
import ua.kpi.comsys.ip8408.feature_filmlist.data.model.FilmsResponse

interface FilmsApi {
    @GET("/")
    suspend fun getFilms(
        @Query("s") request: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("page") page: Int = 1,
    ): FilmsResponse

    @GET("/")
    suspend fun getFilm(
        @Query("i") id: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): FilmDetailedResponse

    companion object {
        private const val API_KEY = "48404aa9"
    }
}