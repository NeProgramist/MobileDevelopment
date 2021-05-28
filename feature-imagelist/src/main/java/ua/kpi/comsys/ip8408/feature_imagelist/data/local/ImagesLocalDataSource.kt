package ua.kpi.comsys.ip8408.feature_imagelist.data.local

import android.util.Log
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import ua.kpi.comsys.ip8408.feature_imagelist.core.datasource.ImagesDataSource
import ua.kpi.comsys.ip8408.feature_imagelist.data.local.db.ImagesDao
import ua.kpi.comsys.ip8408.feature_imagelist.data.local.db.ImagesEntity
import ua.kpi.comsys.ip8408.feature_imagelist.data.mappers.toImageInfo
import ua.kpi.comsys.ip8408.feature_imagelist.data.mappers.toImagesEntity
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

class ImagesLocalDataSource(private val imagesDao: ImagesDao): ImagesDataSource {
    private val tag = this::class.simpleName

    override suspend fun getImages() = try {
        val images = imagesDao.getImages().map(ImagesEntity::toImageInfo).asReversed()
        Ok(images)
    } catch (e: Exception) {
        Log.e(tag, "getImages: ${e.message}")
        Err(Exception("Can't get image's url from cache"))
    }

    override suspend fun saveImages(images: List<ImageInfo>) {
        try {
            val imagesEntity = images.map(ImageInfo::toImagesEntity)
            imagesDao.insertAll(imagesEntity)
        } catch (e: Exception) {
            Log.e(tag, "saveImages: ${e.message}")
        }
    }

    override suspend fun saveImage(image: ImageInfo) {
        try {
            val imageEntity = image.toImagesEntity()
            imagesDao.insert(imageEntity)
        } catch (e: Exception) {
            Log.e(tag, "saveImage: ${e.message}")
        }
    }
}
