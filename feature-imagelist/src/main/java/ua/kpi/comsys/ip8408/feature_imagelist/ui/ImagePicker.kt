package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class ImagePicker(
    private val registry: ActivityResultRegistry,
    private val callback: (Uri?) -> Unit
) : DefaultLifecycleObserver {
    private var galleryListener: ActivityResultLauncher<String>? = null

    override fun onCreate(owner: LifecycleOwner) {
        galleryListener = registry.register("image", owner, ActivityResultContracts.GetContent()) {
            callback(it)
        }
    }

    fun selectFromGallery() {
        galleryListener?.launch("image/*")
    }
}
