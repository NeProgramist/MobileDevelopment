package ua.kpi.comsys.ip8408.feature_filmlist.ui.film_list

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import ua.kpi.comsys.ip8408.feature_filmlist.R
import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.databinding.DialogAddFilmBinding

class AddFilmDialog : DialogFragment() {
    private var _binding: DialogAddFilmBinding? = null
    private val binding get() = _binding!!

    private var onFilmAdded: ((Film) -> Unit)? = null

    fun setOnSuccessListener(f: (Film) -> Unit) {
        onFilmAdded = f
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE, R.style.Dialog)

        return super.onCreateDialog(savedInstanceState).apply {
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpNewFilm()
    }

    private fun setUpNewFilm() = with(binding) {
        var filmTitle = ""
        var filmYear = ""
        var filmType = ""
        var filmId = ""

        year.addTextChangedListener { filmYear = it.toString() }
        type.addTextChangedListener { filmType = it.toString() }
        id.addTextChangedListener { filmId = it.toString() }
        title.addTextChangedListener {
            filmTitle = it.toString()
            addNewFilmBtn.isEnabled = !it.isNullOrBlank()
        }

        dialogButtonClose.setOnClickListener {
            dismiss()
        }

        addNewFilmBtn.setOnClickListener {
            val film = Film(
                poster = "",
                title = filmTitle,
                year = filmYear,
                type = filmType,
                imdbId = filmId,
            )

            onFilmAdded?.invoke(film)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
