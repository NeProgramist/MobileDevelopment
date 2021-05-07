package ua.kpi.comsys.ip8408.feature_plots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import ua.kpi.comsys.ip8408.core_ui.utils.AnimationSet
import ua.kpi.comsys.ip8408.core_ui.utils.changeFragment
import ua.kpi.comsys.ip8408.core_ui.utils.getAnimationSet
import ua.kpi.comsys.ip8408.feature_plots.R
import ua.kpi.comsys.ip8408.feature_plots.databinding.FragmentPlotsBinding
import ua.kpi.comsys.ip8408.feature_plots.ui.diagram.DiagramFragment
import ua.kpi.comsys.ip8408.feature_plots.ui.graph.GraphFragment

class PlotsFragment : Fragment() {
    private var _binding: FragmentPlotsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlotsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPlotsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stage.observe(viewLifecycleOwner) {
            toggleButton(it)
            when (it) {
                PlotsStage.Graph -> graph()
                PlotsStage.Diagram -> diagram()
            }
        }

        binding.graphBtn.setOnClickListener {
            viewModel.changeStage(PlotsStage.Graph)
        }

        binding.diagramBtn.setOnClickListener {
            viewModel.changeStage(PlotsStage.Diagram)
        }
    }

    private fun graph() {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        childFragmentManager.changeFragment(
            fragment = GraphFragment(),
            container = R.id.graph_container,
            backStack = false,
            animationSet = animationSet,
        )
    }

    private fun diagram() {
        val animationSet = getAnimationSet(viewModel.stage.value, viewModel.prev)

        childFragmentManager.changeFragment(
            fragment = DiagramFragment(),
            container = R.id.graph_container,
            backStack = false,
            animationSet = animationSet,
        )
    }

    private fun toggleButton(stage: PlotsStage) = with(binding) {
        val value = stage == PlotsStage.Graph

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

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
