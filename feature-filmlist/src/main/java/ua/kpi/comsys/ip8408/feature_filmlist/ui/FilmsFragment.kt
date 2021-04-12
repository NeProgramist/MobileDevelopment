package ua.kpi.comsys.ip8408.feature_filmlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import ua.kpi.comsys.ip8408.core_ui.utils.AnimationSet
import ua.kpi.comsys.ip8408.core_ui.utils.changeChildFragment
import ua.kpi.comsys.ip8408.feature_filmlist.R
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.FragmentFilmsBinding
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsState.*
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

        viewModel.state.observe(viewLifecycleOwner, ::changeState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeState(state: FilmsState) = when(state) {
        FilmList -> filmList()
        is FilmDetailed -> filmDetailed(state.id)
        Cancel -> requireActivity().onBackPressed()
    }

    private fun filmList() {
        changeChildFragment(FilmListFragment(), R.id.container, true, AnimationSet())
    }

    private fun filmDetailed(id: String) {
        val fragment = FilmDetailedFragment.newInstance(id)
        changeChildFragment(fragment, R.id.container, false, AnimationSet())
    }
}
