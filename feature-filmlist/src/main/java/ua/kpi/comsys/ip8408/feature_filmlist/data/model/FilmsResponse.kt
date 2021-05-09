package ua.kpi.comsys.ip8408.feature_filmlist.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

@Serializable
class FilmsResponse(
    @SerialName("Response")
    val success: String,
    @SerialName("Error")
    val error: String? = null,
    @SerialName("Search")
    val result: List<Film>? = null,
)