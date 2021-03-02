package ua.kpi.comsys.ip8408.feature_plots.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlotsViewModel : ViewModel() {
    val state = MutableLiveData(PlotsState.Graph)
}