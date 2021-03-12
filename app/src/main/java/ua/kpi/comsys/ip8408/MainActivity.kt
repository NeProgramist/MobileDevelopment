package ua.kpi.comsys.ip8408

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8408.ApplicationState.*
import ua.kpi.comsys.ip8408.databinding.ActivityMainBinding
import ua.kpi.comsys.ip8408.feature_plots.ui.PlotsFragment
import ua.kpi.comsys.ip8408.feature_student.ui.StudentFragment
import ua.kpi.comsys.ip8408.core_ui.utils.AnimationSet
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmFragment
import ua.kpi.comsys.ip8408.feature_plots.ui.PlotsViewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)

        viewModel.state.observe(
            this,
            { changeState(it) }
        )

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.student -> {
                    viewModel.state.postValue(StudentInfo)
                    true
                }
                R.id.plots -> {
                    viewModel.state.postValue(Plots)
                    true
                }
                R.id.films -> {
                    viewModel.state.postValue(FilmList)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun changeState(state: ApplicationState) {
        val nextState = state.id

        when(state) {
            StudentInfo -> onMenuItemClicked(nextState, StudentFragment())
            Plots -> onMenuItemClicked(nextState, PlotsFragment())
            FilmList -> onMenuItemClicked(nextState, FilmFragment())
        }

        viewModel.prevState = nextState
    }

    private fun onMenuItemClicked(next: Int, fragment: Fragment) {
        val state = next - viewModel.prevState
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
