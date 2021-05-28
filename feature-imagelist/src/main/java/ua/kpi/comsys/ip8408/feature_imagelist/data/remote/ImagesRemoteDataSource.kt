package ua.kpi.comsys.ip8408.feature_imagelist.data.remote

import android.util.Log
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import ua.kpi.comsys.ip8408.feature_imagelist.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

class ImagesRemoteDataSource(private val api: ImagesApi): ImagesDataSource {
    private val tag = this::class.simpleName

    override suspend fun getImages() = try {
        val images = api.getImages().images

        if (images.isNotEmpty()) Ok(images) else Err(Exception("No images"))
    } catch (e: Exception) {
        Log.e(tag, "getImages: ${e.message}")
        Err(Exception("Can't get image's url from server"))
    }

    override suspend fun saveImages(images: List<ImageInfo>) = Unit
    override suspend fun saveImage(image: ImageInfo) = Unit
}