package ua.kpi.comsys.ip8408.feature_imagelist.data.mappers

import android.net.Uri
import ua.kpi.comsys.ip8408.feature_imagelist.data.model.ImageInfo

fun List<ImageInfo>.toUri() = map { Uri.parse(it.url) }