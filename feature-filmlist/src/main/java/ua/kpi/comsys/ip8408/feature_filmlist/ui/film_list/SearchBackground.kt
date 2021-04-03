package ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list

import android.content.Context
import android.graphics.drawable.DrawableContainer
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.StateListDrawable
import androidx.core.content.ContextCompat
import net.aquadc.fiftyshades.RectShadow
import net.aquadc.fiftyshades.ShadowSpec
import ua.kpi.comsys.ip8408.core_ui.utils.dp
import ua.kpi.comsys.ip8408.feature_filmlist.R

class SearchBackground(context: Context) {
    val background: DrawableContainer = StateListDrawable().apply {
        addState(intArrayOf(android.R.attr.state_focused),
            LayerDrawable(arrayOf(
                RectShadow(15.dp,
                    ShadowSpec(
                        0.dp.toFloat(),
                        0.dp.toFloat(),
                        10.dp.toFloat(),
                        ContextCompat.getColor(context, R.color.melrose)
                    )
                ),
                GradientDrawable().apply {
                    setColor(ContextCompat.getColor(context, R.color.white))
                    setStroke(2.dp, ContextCompat.getColor(context, R.color.melrose))
                    cornerRadius = 15.dp.toFloat()
                }
            ))
        )
        addState(intArrayOf(),
            GradientDrawable().apply {
                setColor(ContextCompat.getColor(context, R.color.white))
                cornerRadius = 15.dp.toFloat()
            }
        )
    }
}
