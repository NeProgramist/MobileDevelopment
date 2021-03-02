package ua.kpi.comsys.ip8408.feature_plots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8408.core_ui.utils.AnimationSet
import ua.kpi.comsys.ip8408.core_ui.utils.changeChildFragment
import ua.kpi.comsys.ip8408.feature_plots.R
import ua.kpi.comsys.ip8408.feature_plots.databinding.FragmentPlotsBinding
import ua.kpi.comsys.ip8408.feature_plots.ui.diagram.DiagramFragment
import ua.kpi.comsys.ip8408.feature_plots.ui.graph.GraphFragment

class PlotsFragment : Fragment() {
    private var _binding: FragmentPlotsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PlotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPlotsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(PlotsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(
            viewLifecycleOwner,
            { changeState(it) }
        )

        binding.graphBtn.setOnClickListener {
            viewModel.state.postValue(PlotsState.Graph)
        }
        binding.diagramBtn.setOnClickListener {
            viewModel.state.postValue(PlotsState.Diagram)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun changeState(state: PlotsState) {
        toggleButton(state)
        when (state) {
            PlotsState.Graph -> graph()
            PlotsState.Diagram -> diagram()
        }
    }

    private fun graph() {
        changeChildFragment(
            GraphFragment(),
            R.id.graph_container,
            animationSet = AnimationSet(
                R.anim.slide_from_left,
                R.anim.slide_to_right
            )
        )
    }

    private fun diagram() {
        changeChildFragment(
            DiagramFragment(),
            R.id.graph_container,
            animationSet = AnimationSet()
        )
    }

    private fun toggleButton(state: PlotsState) = with(binding) {
        val value = state == PlotsState.Graph

        graphBtn.isClickable = !value
        diagramBtn.isClickable = value

        val (graphBtnColor, diagramBtnColor) = if (value) {
            R.color.pink to R.color.white
        }
        else {
            R.color.white to R.color.pink
        }

        graphBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), graphBtnColor))
        diagramBtn.setBackgroundColor(ContextCompat.getColor(requireContext(), diagramBtnColor))
    }
}
