package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository

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
}
