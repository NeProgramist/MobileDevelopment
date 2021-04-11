package ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.FragmentFilmListBinding
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsState
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsViewModel

class FilmListFragment : Fragment() {
    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private val flowViewModel: FilmsViewModel by sharedViewModel()
    private val viewModel: FilmListViewModel by viewModel()

    private lateinit var adapter: FilmListAdapter
    private lateinit var searchBackground: SearchBackground

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBackground = SearchBackground(requireContext())
        binding.search.background = searchBackground.background

        setUpAdapter()
        setUpDialog()

        viewModel.films.observe(viewLifecycleOwner, { res ->
            adapter.updateDataSet(res)
            binding.filmsError.visibility = View.GONE
        })

        viewModel.filmsException.observe(viewLifecycleOwner, { e ->
            adapter.updateDataSet(emptyList())
            binding.filmsError.text = e.message
            binding.filmsError.visibility = View.VISIBLE
        })

        binding.search.addTextChangedListener { viewModel.onTextChanged(it.toString()) }

        viewModel.getFilms()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
        viewModel.films.removeObservers(viewLifecycleOwner)
        viewModel.filmsException.removeObservers(viewLifecycleOwner)
    }

    private fun setUpAdapter() {
        val onItemClick = { id: String ->
            flowViewModel.state.postValue(FilmsState.FilmDetailed(id))
        }

        adapter = FilmListAdapter(
            assetsReader = get(),
            onClick = onItemClick,
            remoteItemCallback = viewModel::removeFilm
        )

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
        ItemTouchHelper(
            DeleteCallback(requireContext(), adapter)
        ).attachToRecyclerView(binding.recycler)
    }

    private fun setUpDialog() {
        binding.addNewFilm.setOnClickListener {
            val dialog = AddFilmDialog()

            dialog.setOnSuccessListener { film ->
                viewModel.addFilm(film)
            }

            dialog.show(childFragmentManager, "ADD_FILM_DIALOG")
        }
    }
}
