package ua.kpi.comsys.ip8408.feature_filmlist.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    @SerialName("Title")
    val title: String,
    @SerialName("Year")
    val year: String,
    @SerialName("imdbID")
    val imdbId: String,
    @SerialName("Type")
    val type: String,
    @SerialName("Poster")
    val poster: String,
)
