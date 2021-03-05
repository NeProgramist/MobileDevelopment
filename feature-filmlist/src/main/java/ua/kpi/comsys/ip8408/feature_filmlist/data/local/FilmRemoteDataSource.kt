package ua.kpi.comsys.ip8408.feature_filmlist.data.local

import android.content.Context
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.model.Search

class FilmRemoteDataSource(private val context: Context) : FilmDataSource {
    override suspend fun getFilmList(fileName: String) = try {
        val file = context.assets.open(fileName).bufferedReader()
        val data = file.readText()

        val films = Json.decodeFromString<Search>(data)
        Ok(films.values)
    } catch (e: Exception) {
        Err(e)
    }
}
