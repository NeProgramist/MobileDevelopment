package ua.kpi.comsys.ip8408

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.viewModel
import ua.kpi.comsys.ip8408.ApplicationStage.*
import ua.kpi.comsys.ip8408.databinding.ActivityMainBinding
import ua.kpi.comsys.ip8408.feature_plots.ui.PlotsFragment
import ua.kpi.comsys.ip8408.feature_student.ui.StudentFragment
import ua.kpi.comsys.ip8408.core_ui.utils.PermissionActivity
import ua.kpi.comsys.ip8408.core_ui.utils.changeFragment
import ua.kpi.comsys.ip8408.core_ui.utils.getAnimationSet
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsFragment
import ua.kpi.comsys.ip8408.feature_imagelist.ui.ImageListFragment

class MainActivity : PermissionActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.stage.observe(this) {
            val fragment = when(it) {
                StudentInfo -> StudentFragment()
                Plots -> PlotsFragment()
                FilmList -> FilmsFragment()
                ImageList -> ImageListFragment()
            }

            changeStageFragment(fragment)
        }

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.student -> {
                    viewModel.changeStage(StudentInfo)
                    true
                }
                R.id.plots -> {
                    viewModel.changeStage(Plots)
                    true
                }
                R.id.films -> {
                    viewModel.changeStage(FilmList)
                    true
                }
                R.id.images -> {
                    viewModel.changeStage(ImageList)
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

    private fun changeStageFragment(fragment: Fragment) {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        supportFragmentManager.changeFragment(
            fragment = fragment,
            container = R.id.fragment_container,
            backStack = false,
            animationSet = animationSet
        )
    }
}
