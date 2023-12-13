package com.example.anikash.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.anikash.MainActivity
import com.example.anikash.databinding.FragmentDashboardBinding
import org.json.JSONObject

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var nameField: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.titleText
        nameField = binding.nameField


        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.submitButton.setOnClickListener {
            val jsonObject = JSONObject()

            val enteredName = binding.nameField.text
            jsonObject.put("Name", enteredName)
            val enteredOrg = binding.currentOrganisationField.text
            jsonObject.put("Organisation", enteredOrg)
            val enteredPos = binding.currentPositionField.text
            jsonObject.put("Position", enteredPos)

            println(jsonObject.toString())
            (activity as MainActivity).appendPortfolio(jsonObject);

            // change to home fragment
            (activity as MainActivity).goToHome()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
