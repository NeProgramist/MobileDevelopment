package ua.kpi.comsys.ip8408.feature_filmlist.ui.detailed

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.michaelbull.result.fold
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.get
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import ua.kpi.comsys.ip8408.data.frameworks.local.AssetsReader
import ua.kpi.comsys.ip8408.feature_filmlist.R
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.FragmentFilmDetailedBinding
import ua.kpi.comsys.ip8408.feature_filmlist.ui.FilmsViewModel

class FilmDetailedFragment : Fragment() {
    private var _binding: FragmentFilmDetailedBinding? = null
    private val binding get() = _binding!!

    private val flowViewModel: FilmsViewModel by sharedViewModel()
    private val viewModel: FilmDetailedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            flowViewModel.back()
        }

        viewModel.film.observe(viewLifecycleOwner, ::setUpFilm)

        viewModel.filmException.observe(viewLifecycleOwner) { e ->
            binding.scrollView.visibility = View.GONE
            binding.poster.visibility = View.GONE
            binding.filmError.visibility = View.VISIBLE
            binding.filmError.text = getString(R.string.film_error, e.message)

            binding.loader.isVisible = false
        }

        val id = arguments?.getString(ID) ?: error("Property hasn't been initialized")
        viewModel.getFilm(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        viewModel.film.removeObservers(viewLifecycleOwner)
        viewModel.filmException.removeObservers(viewLifecycleOwner)
    }

    private fun setUpFilm(film: Film) = with(binding) {
        Picasso.get()
            .load(film.poster)
            .into(poster)

        if (film.year.isNotBlank()) year.text = film.year
        if (film.title.isNotBlank()) title.text = film.title
        if (film.imdbId.isNotBlank()) id.text = film.imdbId
        if (film.plot?.isNotBlank() == true) plot.text = film.plot

        rated.setTextOrGone(film.rated, R.string.film_rated)
        type.setTextOrGone(film.type, R.string.film_type)
        id.setTextOrGone(film.imdbId, R.string.film_id)
        rated.setTextOrGone(film.rated, R.string.film_rated)
        released.setTextOrGone(film.released, R.string.film_released)
        duration.setTextOrGone(film.duration, R.string.film_duration)
        genres.setTextOrGone(film.genres, R.string.film_genres)
        director.setTextOrGone(film.director, R.string.film_director)
        writer.setTextOrGone(film.writer, R.string.film_writer)
        actors.setTextOrGone(film.actors, R.string.film_actors)
        language.setTextOrGone(film.language, R.string.film_language)
        country.setTextOrGone(film.country, R.string.film_country)
        awards.setTextOrGone(film.awards, R.string.film_awards)
        imdbRating.setTextOrGone(film.imdbRating, R.string.film_imdbRating)
        imdbVotes.setTextOrGone(film.imdbVotes, R.string.film_imdbVotes)
        production.setTextOrGone(film.production, R.string.film_production)

        typeId.isVisible = true
        loader.isVisible = false
    }

    private fun TextView.setTextOrGone(field: String?, resource: Int) {
        if (field?.isNotBlank() == true) {
            text = Html.fromHtml(getString(resource, field))
        } else {
            visibility = View.GONE
        }
    }

    companion object {
        const val ID = "ID"

        fun newInstance(id: String) = FilmDetailedFragment().also {
            it.arguments = bundleOf(
                ID to id
            )
        }
    }
}
