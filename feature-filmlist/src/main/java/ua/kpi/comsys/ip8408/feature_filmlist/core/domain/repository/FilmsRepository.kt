package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.onSuccess
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film

class FilmsRepository(private val local: FilmsDataSource) {
    private var films = listOf<Film>()

    suspend fun getFilms(): Result<List<Film>, Exception> {
        return if (films.isEmpty()) {
            local.getFilmList().also { res ->
                res.onSuccess { films = it }
            }
        } else {
            Ok(films)
        }
    }

    suspend fun getFilm(id: String) = local.getFilm(id)

    fun removeFilm(film: Film): Result<Unit, Exception> {
        val ind = films.indexOf(film)
        return if (ind > -1) {
            films = films.filterIndexed { index, _ -> index != ind }
            Ok(Unit)
        } else {
            Err(Exception("Cannot delete item: deleted item out of range"))
        }
    }

    fun addFilm(film: Film): Result<Unit, Exception> {
        films = films + film

        return if (films.last() == film) {
            Ok(Unit)
        } else {
            Err(Exception("Film wasn't added"))
        }
    }
}
