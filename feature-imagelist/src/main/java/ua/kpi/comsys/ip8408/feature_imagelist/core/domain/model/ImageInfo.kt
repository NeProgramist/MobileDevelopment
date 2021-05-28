package ua.kpi.comsys.ip8408.feature_imagelist.core.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ImageInfo(
    val id: Int,
    @SerialName("webformatURL") val url: String,
)
