package ua.kpi.comsys.ip8408.core_ui.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class StageViewModel<T : Stage>(defaultValue: T? = null) : ViewModel() {
    var prev: T? = null
    val stage = MutableLiveData<T>().apply {
        if (defaultValue != null) value = defaultValue
    }

    fun changeStage(nextStage: T) {
        val current = stage.value
        if (current != nextStage) {
            prev = current
            stage.value = nextStage
        }
    }
}