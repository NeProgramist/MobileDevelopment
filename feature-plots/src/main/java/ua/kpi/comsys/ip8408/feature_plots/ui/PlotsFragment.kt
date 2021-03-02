package ua.kpi.comsys.ip8408.feature_plots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel = ViewModelProvider(this).get(PlotsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(
            viewLifecycleOwner,
            { changeState(it) }
        )

        binding.graphToggleBtn.check(R.id.graph_btn)
        binding.graphToggleBtn.addOnButtonCheckedListener { _, checkedId, _ ->
            when (checkedId) {
                R.id.graph_btn -> {
                    viewModel.state.postValue(PlotsState.Graph)
                }
                R.id.diagram_btn -> {
                    viewModel.state.postValue(PlotsState.Diagram)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeState(state: PlotsState) {
        when(state) {
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

    companion object {
        const val id = 1
    }
}
