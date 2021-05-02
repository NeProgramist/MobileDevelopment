package ua.kpi.comsys.ip8408.core_ui.utils

import android.content.res.Resources
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