package ua.kpi.comsys.ip8408.feature_filmlist.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val request: String,
    val title: String,
    val year: String,
    val type: String,
    @ColumnInfo(name = "imdb_id") val imdbId: String,
    val poster: String,
)

@Entity(tableName = "films_detailed")
data class FilmDetailedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "imdb_id") val imdbId: String,
    val rated: String? = null,
    val released: String? = null,
    val duration: String? = null,
    val genres: String? = null,
    val director: String? = null,
    val writer: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val language: String? = null,
    val country: String? = null,
    val awards: String? = null,
    @ColumnInfo(name = "imdb_rating") val imdbRating: String? = null,
    @ColumnInfo(name = "imdb_votes") val imdbVotes: String? = null,
    val production: String? = null,
)
