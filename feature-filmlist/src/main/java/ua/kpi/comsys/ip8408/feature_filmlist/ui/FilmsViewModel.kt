package ua.kpi.comsys.ip8408.feature_filmlist.ui

import ua.kpi.comsys.ip8408.core_ui.utils.StageViewModel

class FilmsViewModel : StageViewModel<FilmsStage>() {
    fun back() {
        val next: FilmsStage = when(stage.value) {
            is FilmsStage.FilmDetailed -> FilmsStage.FilmList
            else -> FilmsStage.Cancel
        }

        changeStage(next)
    }
}
