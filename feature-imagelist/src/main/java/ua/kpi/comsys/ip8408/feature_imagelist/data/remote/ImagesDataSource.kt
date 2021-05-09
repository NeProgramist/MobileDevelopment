package ua.kpi.comsys.ip8408.feature_imagelist.data.remote

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_imagelist.data.model.ImageInfo

class ImagesDataSource(private val api: ImagesApi) {
    suspend fun getImages(): Result<List<ImageInfo>, Exception> {
        val images = api.getImages().images

        return if (images.isNotEmpty()) Ok(images) else Err(Exception("No images"))
    }
}