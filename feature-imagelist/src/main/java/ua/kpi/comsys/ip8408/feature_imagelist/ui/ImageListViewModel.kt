package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageListViewModel : ViewModel() {
    val images = MutableLiveData<List<Uri>>()

    init {
        print("")
    }

    fun addNewImage(uri: Uri) {
        images.value = (images.value ?: emptyList()) + uri
    }
}