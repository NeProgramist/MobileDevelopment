package ua.kpi.comsys.ip8408.feature_filmlist.data.local.db

import androidx.room.*

@Dao
interface FilmsDao {
    @Query("""
        select title, year, type, imdb_id, poster from films 
        where films.title like '%' || :request || '%'
    """)
    fun getFilmList(request: String): List<FilmBasic>

    @Query("select * from films where imdb_id = :imdbId")
    fun getFilmDetailed(imdbId: String): FilmEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(filmList: List<FilmEntity>)

    @Update
    fun update(film: FilmEntity)
}
