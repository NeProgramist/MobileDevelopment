package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ua.kpi.comsys.ip8408.feature_imagelist.databinding.FragmentImageListBinding

class ImageListFragment : Fragment() {
    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ImageListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ImageListAdapter()

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = ImageListLayoutManager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
