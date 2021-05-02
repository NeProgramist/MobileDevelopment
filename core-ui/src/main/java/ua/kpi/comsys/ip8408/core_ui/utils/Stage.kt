package ua.kpi.comsys.ip8408.core_ui.utils

import ua.kpi.comsys.ip8408.core_ui.R

abstract class Stage(val weight: Int)

operator fun Stage.compareTo(other: Stage?) : Int {
    return this.weight - (other?.weight ?: 0)
}

fun getAnimationSet(cur: Stage?, prev: Stage?) = when {
    prev == null -> {
        AnimationSet(
            animEnter = R.anim.no_anim,
            animExit = R.anim.slide_to_left,
            popEnter = R.anim.no_anim,
            popExit = R.anim.slide_to_right
        )
    }
    prev <= cur -> {
        AnimationSet(
            animEnter = R.anim.slide_from_right,
            animExit = R.anim.slide_to_left,
            popEnter = R.anim.slide_from_left,
            popExit = R.anim.slide_to_right
        )
    }
    else -> {
        AnimationSet(
            animEnter = R.anim.slide_from_left,
            animExit = R.anim.slide_to_right,
            popEnter = R.anim.slide_from_right,
            popExit = R.anim.slide_to_left
        )
    }
}
