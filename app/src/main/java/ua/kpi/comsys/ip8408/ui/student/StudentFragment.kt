package ua.kpi.comsys.ip8408.ui.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ua.kpi.comsys.ip8408.R
import ua.kpi.comsys.ip8408.databinding.ActivityMainBinding
import ua.kpi.comsys.ip8408.databinding.FragmentStudentBinding

class StudentFragment : Fragment() {
    private var _binding: FragmentStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val id = 0
    }
}
