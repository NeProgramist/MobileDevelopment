package ua.kpi.comsys.ip8408.feature_plots.ui.graph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.kpi.comsys.ip8408.feature_plots.databinding.ItemGraphBinding
import kotlin.math.ln

class GraphFragment : Fragment() {
    private var _binding: ItemGraphBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemGraphBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.graph.function = { x -> ln(x) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
