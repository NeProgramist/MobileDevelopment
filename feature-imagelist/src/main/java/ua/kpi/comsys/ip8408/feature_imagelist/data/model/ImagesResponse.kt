package ua.kpi.comsys.ip8408.feature_imagelist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model.ImageInfo

@Serializable
class ImagesResponse(
    @SerialName("hits")
    val images: List<ImageInfo>
)
