package ua.kpi.comsys.ip8408.feature_filmlist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilmsViewModel : ViewModel() {
    val state = MutableLiveData<FilmsState>(FilmsState.FilmList)

    fun back() {
        val next: FilmsState = when(state.value) {
            is FilmsState.FilmDetailed -> FilmsState.FilmList
            else -> FilmsState.Cancel
        }

        state.value = next
    }
}
