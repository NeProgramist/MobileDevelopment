package ua.kpi.comsys.ip8408.feature_imagelist.core.datasource

import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

interface ImagesDataSource {
    suspend fun getImages(): Result<List<ImageInfo>, Exception>
    suspend fun saveImages(images: List<ImageInfo>)
    suspend fun saveImage(image: ImageInfo)
}
