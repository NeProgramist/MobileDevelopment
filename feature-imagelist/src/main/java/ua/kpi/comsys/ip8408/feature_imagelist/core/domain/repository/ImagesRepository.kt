package ua.kpi.comsys.ip8408.feature_imagelist.core.domain.repository

import com.github.michaelbull.result.onSuccess
import com.github.michaelbull.result.orElse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.kpi.comsys.ip8408.feature_imagelist.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

class ImagesRepository(
    private val local: ImagesDataSource,
    private val remote: ImagesDataSource,
) {
    suspend fun getImages() = withContext(Dispatchers.IO) {
        remote.getImages()
            .onSuccess { local.saveImages(it) }
            .orElse { local.getImages() }
    }

    suspend fun addImage(image: ImageInfo) = withContext(Dispatchers.IO) {
        local.saveImage(image)
    }
}
