package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model

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
    @SerialName("Rated")
    val rated: String? = null,
    @SerialName("Released")
    val released: String? = null,
    @SerialName("Runtime")
    val duration: String? = null,
    @SerialName("Genre")
    val genres: String? = null,
    @SerialName("Director")
    val director: String? = null,
    @SerialName("Writer")
    val writer: String? = null,
    @SerialName("Actors")
    val actors: String? = null,
    @SerialName("Plot")
    val plot: String? = null,
    @SerialName("Language")
    val language: String? = null,
    @SerialName("Country")
    val country: String? = null,
    @SerialName("Awards")
    val awards: String? = null,
    @SerialName("imdbRating")
    val imdbRating: String? = null,
    @SerialName("imdbVotes")
    val imdbVotes: String? = null,
    @SerialName("Production")
    val production: String? = null,
)
