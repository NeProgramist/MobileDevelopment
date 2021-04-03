package ua.kpi.comsys.ip8408.core_ui.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ua.kpi.comsys.ip8408.core_ui.R

fun Fragment.changeChildFragment(
    fragment: Fragment,
    container: Int,
    backStack: Boolean = false,
    animationSet: AnimationSet,
) {
    childFragmentManager.commit {
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