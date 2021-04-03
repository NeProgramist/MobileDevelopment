package ua.kpi.comsys.ip8408.feature_filmlist.ui

sealed class FilmsState {
    object Cancel : FilmsState()
    object FilmList : FilmsState()
    data class FilmDetailed(val id: String) : FilmsState()
}
