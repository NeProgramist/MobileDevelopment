package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.fold
import kotlinx.coroutines.launch
import ua.kpi.comsys.ip8408.feature_imagelist.data.mappers.toUri
import ua.kpi.comsys.ip8408.feature_imagelist.data.remote.ImagesDataSource

class ImageListViewModel(private val imageDS: ImagesDataSource) : ViewModel() {
    val images = MutableLiveData<List<Uri>>()
    val imagesException = MutableLiveData<Exception>()

    fun addNewImage(uri: Uri) {
        images.value = (images.value ?: emptyList()) + uri
    }

    fun getImages() {
        viewModelScope.launch {
            imageDS.getImages().fold(
                { images.postValue(it.toUri()) },
                { imagesException.postValue(it) },
            )
        }
    }
}