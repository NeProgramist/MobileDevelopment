package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository

import com.github.michaelbull.result.*
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.FilmsLocalDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.data.remote.FilmsRemoteDataSource

class FilmsRepository(
    private val local: FilmsDataSource,
    private val remote: FilmsDataSource,
) {
    private var films = listOf<Film>()

    suspend fun getFilms(request: String) = remote.getFilmList(request).onSuccess { films = it }

    suspend fun getFilm(id: String) = remote.getFilm(id)

    fun restoreFilms() = if (films.isNotEmpty()) Ok(films) else Err(Exception("No films"))

    fun removeFilm(film: Film): Result<Unit, Exception> {
        val ind = films.indexOf(film)
        return if (ind > -1) {
            films = films.filterIndexed { index, _ -> index != ind }
            Ok(Unit)
        } else {
            Err(Exception("Cannot delete item: deleted item out of range"))
        }
    }

    fun addFilm(film: Film): Result<List<Film>, Exception> {
        films = films + film

        return if (films.last() == film) {
            Ok(films)
        } else {
            Err(Exception("Film wasn't added"))
        }
    }
}
