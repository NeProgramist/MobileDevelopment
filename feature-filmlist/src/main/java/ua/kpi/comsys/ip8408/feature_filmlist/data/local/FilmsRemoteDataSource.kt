package ua.kpi.comsys.ip8408.feature_filmlist.data.local

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.fold
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8408.data.frameworks.local.AssetsReader
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.model.Search
import java.lang.Exception

class FilmsRemoteDataSource(private val assetsReader: AssetsReader) : FilmsDataSource {
    private val fileName = "MoviesList.txt"

    override suspend fun getFilmList() = assetsReader.read(fileName).fold(
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
}
