package ua.kpi.comsys.ip8408.feature_filmlist.core.domain.interceptor

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.repository.FilmsRepository

class FilmsInterceptor(private val filmsRepository: FilmsRepository) {
    suspend fun getFilms() = filmsRepository.getFilms()

    suspend fun searchFilms(q: String): Result<List<Film>, Exception> {
        val films = getFilms()

        return if (films is Ok) {
            val first = films.value.filter { item -> item.title.startsWith(q, true) }
            val second = films.value.filter { item ->
                !item.title.startsWith(q, true) && item.title.contains(q, true)
            }

            val res = first + second

            if (res.isNotEmpty()) Ok(res) else Err(Exception("Can`t find any suitable film"))
        } else {
            films
        }
    }
}
