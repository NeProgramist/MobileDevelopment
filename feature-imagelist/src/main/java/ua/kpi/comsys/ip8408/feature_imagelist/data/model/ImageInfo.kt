package ua.kpi.comsys.ip8408.feature_imagelist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ImageInfo(
    @SerialName("webformatURL")
    val url: String,
)