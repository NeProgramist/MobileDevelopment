package ua.kpi.comsys.ip8408.feature_imagelist.data.mappers

import android.net.Uri
import ua.kpi.comsys.ip8408.feature_imagelist.data.local.db.ImagesEntity
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

fun ImageInfo.toUri(): Uri = Uri.parse(url)

fun ImageInfo.toImagesEntity() = ImagesEntity(
    id = id,
    imageUrl = url,
)

fun ImagesEntity.toImageInfo() = ImageInfo(
    id = id,
    url = imageUrl,
)
