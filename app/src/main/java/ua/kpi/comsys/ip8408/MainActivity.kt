package ua.kpi.comsys.ip8408

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ua.kpi.comsys.ip8408.databinding.ActivityMainBinding
import ua.kpi.comsys.ip8408.feature_plots.ui.PlotsFragment
import ua.kpi.comsys.ip8408.feature_student.ui.StudentFragment
import ua.kpi.comsys.ip8408.core_ui.utils.AnimationSet

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureFields()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun configureFields() = with(binding) {
        val studentFragment = StudentFragment()
        val plotsFragment = PlotsFragment()

        var state = 0
        activityChangeFragment(studentFragment, animationSet = AnimationSet())

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.student -> {
                    val nextState = StudentFragment.id
                    onMenuItemClicked(state, nextState, studentFragment)
                    state = nextState
                    true
                }
                R.id.plots -> {
                    val nextState = ua.kpi.comsys.ip8408.feature_plots.ui.PlotsFragment.id
                    onMenuItemClicked(state, nextState, plotsFragment)
                    state = nextState
                    true
                }
                else -> false
            }
        }
    }

    private fun onMenuItemClicked(prev: Int, next: Int, fragment: Fragment) {
        val state = next - prev
        when {
            state > 0 -> {
                activityChangeFragment(fragment = fragment, animationSet = AnimationSet())
            }
            state < 0 -> {
                activityChangeFragment(
                    fragment = fragment,
                    animationSet = AnimationSet(
                        R.anim.slide_from_left,
                        R.anim.slide_to_right
                    )
                )
            }
            else -> Unit
        }
    }

    private fun activityChangeFragment(
        fragment: Fragment,
        backStack: Boolean = false,
        animationSet: AnimationSet
    ) {
        supportFragmentManager.commit {
            val (animEnter, animExit, popEnter, popExit) = animationSet
            setCustomAnimations(animEnter, animExit, popEnter, popExit)
            replace(R.id.fragment_container, fragment)
            if (backStack) addToBackStack(fragment::class.simpleName)
        }
    }
}
