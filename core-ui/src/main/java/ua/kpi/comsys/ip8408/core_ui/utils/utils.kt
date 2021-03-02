package ua.kpi.comsys.ip8408.core_ui.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

fun Fragment.changeChildFragment(
    fragment: Fragment,
    container: Int,
    backStack: Boolean = false,
    animationSet: AnimationSet
) {
    childFragmentManager.commit {
        val (animEnter, animExit, popEnter, popExit) = animationSet
        setCustomAnimations(animEnter, animExit, popEnter, popExit)
        replace(container, fragment)
        if (backStack) addToBackStack(fragment::class.simpleName)
    }
}