package ua.kpi.comsys.ip8408.feature_filmlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.FragmentFilmBinding

class FilmFragment : Fragment() {
    private var _binding: FragmentFilmBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(FilmsViewModel::class.java)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
