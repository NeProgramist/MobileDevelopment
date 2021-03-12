package ua.kpi.comsys.ip8408.feature_filmlist.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8408.feature_filmlist.core.datasource.FilmsDataSource
import ua.kpi.comsys.ip8408.feature_filmlist.core.model.Film
import java.lang.Exception

class FilmsViewModel(private val filmsDS: FilmsDataSource) : ViewModel() {
    val films = MutableLiveData<Result<List<Film>, Exception>>()

    fun getFilms() {
        viewModelScope.launch {
            val res = filmsDS.getFilmList()

            films.postValue(res)
        }
    }
}