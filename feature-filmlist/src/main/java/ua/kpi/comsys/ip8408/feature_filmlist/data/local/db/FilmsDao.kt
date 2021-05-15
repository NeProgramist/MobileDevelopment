package ua.kpi.comsys.ip8408.feature_filmlist.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmsDao {
    @Query("select * from films where id = :imdbId")
    suspend fun getFilm(imdbId: String): FilmEntity

    @Query("select * from films where request = :request")
    suspend fun getFilmList(request: String): List<FilmEntity>

    @Query("select * from films_detailed where imdb_id = :imdbId")
    suspend fun getFilmDetailed(imdbId: String): FilmDetailedEntity

    @Insert
    suspend fun insert(film: FilmEntity)

    @Insert
    suspend fun insert(film: FilmDetailedEntity)

    @Insert
    suspend fun insertAll(vararg films: FilmEntity)
}
