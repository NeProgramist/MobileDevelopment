package ua.kpi.comsys.ip8408.core_ui.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

fun FragmentManager.changeFragment(
    fragment: Fragment,
    container: Int,
    backStack: Boolean = false,
    animationSet: AnimationSet,
) {
    this.commit {
        val (animEnter, animExit, popEnter, popExit) = animationSet
        setCustomAnimations(animEnter, animExit, popEnter, popExit)
        replace(container, fragment)
        if (backStack) addToBackStack(fragment::class.simpleName)
    }
}

val Int.dp
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.px
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Context.getImageDrawable(uri: Uri, size: Pair<Int, Int>? = null): BitmapDrawable {
    var bitmap = if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(contentResolver, uri)
    } else {
        val source = ImageDecoder.createSource(contentResolver, uri)
        ImageDecoder.decodeBitmap(source)
    }

    size?.let {
        val (width, height) = it
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
    }

    return BitmapDrawable(resources, bitmap)
}
