package ua.kpi.comsys.ip8408.feature_filmlist.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    @SerialName("Search")
    val values: List<Film>
)
