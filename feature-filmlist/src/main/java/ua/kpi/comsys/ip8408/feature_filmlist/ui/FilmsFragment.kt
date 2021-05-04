package ua.kpi.comsys.ip8408.feature_filmlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import ua.kpi.comsys.ip8408.core_ui.utils.changeFragment
import ua.kpi.comsys.ip8408.core_ui.utils.getAnimationSet
import ua.kpi.comsys.ip8408.feature_filmlist.R
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.FragmentFilmsBinding
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsStage.*
import ua.kpi.comsys.ip8408.feature_filmlist.ui.detailed.FilmDetailedFragment
import ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list.FilmListFragment

class FilmsFragment : Fragment() {
    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilmsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stage.observe(viewLifecycleOwner) {
            when(it) {
                FilmList -> filmList()
                is FilmDetailed -> filmDetailed(it.id)
                Cancel -> requireActivity().onBackPressed()
            }
        }

        viewModel.stage.value = FilmList
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun filmList() {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        childFragmentManager.changeFragment(
            fragment = FilmListFragment(),
            container = R.id.container,
            backStack = true,
            animationSet = animationSet,
        )
    }

    private fun filmDetailed(id: String) {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        val fragment = FilmDetailedFragment.newInstance(id)

        childFragmentManager.changeFragment(
            fragment = fragment,
            container = R.id.container,
            backStack = false,
            animationSet = animationSet,
        )
    }
}
