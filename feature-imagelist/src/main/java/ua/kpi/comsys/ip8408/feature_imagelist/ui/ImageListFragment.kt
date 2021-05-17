package ua.kpi.comsys.ip8408.feature_imagelist.ui

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import ua.kpi.comsys.ip8408.core_ui.utils.PermissionActivity
import ua.kpi.comsys.ip8408.feature_imagelist.databinding.FragmentImageListBinding

class ImageListFragment : Fragment() {
    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ImageListAdapter
    private lateinit var imagePicker: ImagePicker

    private val viewModel: ImageListViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imagePicker = ImagePicker(requireActivity().activityResultRegistry, this::photoSelected)
        lifecycle.addObserver(imagePicker)
    }

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

        binding.addNewImage.setOnClickListener {
            val activity = requireActivity() as PermissionActivity
            activity.permissionCallback = {
                if (it) {
                    imagePicker.selectFromGallery()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "You should grant permission to access gallery",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            activity.requestPermissionLauncher?.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        viewModel.images.observe(viewLifecycleOwner) {
            adapter.updateImageList(it)
            binding.loader.hide()
        }

        viewModel.imagesException.observe(viewLifecycleOwner) { e ->
            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
            binding.loader.hide()
        }

        binding.loader.show()
        viewModel.getImages()
    }

    private fun photoSelected(uri: Uri?) {
        uri?.let {
            viewModel.addImage(it)
            adapter.addImage(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
