package ua.kpi.comsys.ip8408.feature_plots.ui

import ua.kpi.comsys.ip8408.core_ui.utils.Stage

sealed class PlotsStage(weight: Int) : Stage(weight) {
    object Graph : PlotsStage(0)
    object Diagram : PlotsStage(1)
}