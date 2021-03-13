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
    @SerialName("Rated")
    val rated: String?,
    @SerialName("Released")
    val released: String?,
    @SerialName("Runtime")
    val duration: String?,
    @SerialName("Genre")
    val genres: String?,
    @SerialName("Director")
    val director: String?,
    @SerialName("Writer")
    val writer: String?,
    @SerialName("Actors")
    val actors: String?,
    @SerialName("Plot")
    val plot: String?,
    @SerialName("Language")
    val language: String?,
    @SerialName("Country")
    val country: String?,
    @SerialName("Awards")
    val awards: String?,
    @SerialName("imdbRating")
    val imdbRating: String?,
    @SerialName("imdbVotes")
    val imdbVotes: String?,
    @SerialName("Production")
    val production: String?,
)
