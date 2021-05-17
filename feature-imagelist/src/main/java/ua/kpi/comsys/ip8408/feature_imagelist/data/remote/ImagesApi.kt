package ua.kpi.comsys.ip8408.feature_imagelist.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ua.kpi.comsys.ip8408.feature_imagelist.data.model.ImagesResponse

interface ImagesApi {
    @GET("api/")
    suspend fun getImages(
        @Query("q", encoded = false) request: String = "small+animals",
        @Query("key") apiKey: String = API_KEY,
        @Query("per_page") count: Int = 18,
        @Query("image_type") type: String = "photo",
    ): ImagesResponse

    companion object {
        const val API_KEY = "19193969-87191e5db266905fe8936d565"
    }
}