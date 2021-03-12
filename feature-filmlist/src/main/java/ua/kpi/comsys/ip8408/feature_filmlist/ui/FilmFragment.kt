package ua.kpi.comsys.ip8408.feature_filmlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.michaelbull.result.fold
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.FragmentFilmBinding

class FilmFragment : Fragment() {
    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FilmsViewModel by viewModel()
    private val adapter: FilmsAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.films.observe(viewLifecycleOwner, { res ->
            res.fold(
                { adapter.updateDataSet(it) },
                { /* TODO(show message that data cannot be retrieved) */ }
            )
        })

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        viewModel.getFilms()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
