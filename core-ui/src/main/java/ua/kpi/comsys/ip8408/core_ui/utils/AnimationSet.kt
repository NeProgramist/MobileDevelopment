package ua.kpi.comsys.ip8408.core_ui.utils

import androidx.annotation.AnimRes
import ua.kpi.comsys.ip8408.core_ui.R

data class AnimationSet(
    @AnimRes val animEnter: Int = R.anim.slide_from_right,
    @AnimRes val animExit: Int = R.anim.slide_to_left,
    @AnimRes val popEnter: Int = R.anim.slide_from_left,
    @AnimRes val popExit: Int = R.anim.slide_to_right,
)
