package ua.kpi.comsys.ip8408.feature_filmlist.data.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.fold
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8408.data.frameworks.local.AssetsReader
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Search
import kotlin.Exception

class FilmsAssetsDataSource(private val assetsReader: AssetsReader): FilmsDataSource {
    private val fileName = "MoviesList.txt"

    override suspend fun getFilmList(request: String) = assetsReader.read(fileName).fold(
        { data ->
            try {
                val films = Json.decodeFromString<Search>(data)
                Ok(films.values)
            } catch(e: Exception) {
                Err(e)
            }
        },
        { e ->
            Err(e)
        }
    )

    override suspend fun getFilmDetailed(id: String) = assetsReader.read("detailed/$id.txt").fold(
        { data ->
            try {
                val film = Json.decodeFromString<Film>(data)
                Ok(film)
            } catch(e: Exception) {
                Err(e)
            }
        },
        { e ->
            Err(e)
        }
    )

    override suspend fun saveFilmDetailed(film: Film) = Unit
    override suspend fun saveFilmList(filmList: List<Film>) = Unit
    override suspend fun deleteFilm(film: Film) = Unit
}
