package com.example.ehealth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ehealth.application.HealthApp
import com.example.ehealth.databinding.FragmentSecondBinding
import com.example.ehealth.model.Health
import com.example.ehealth.ui.HealthViewModel
import com.example.ehealth.ui.HealthViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val healthViewModel: HealthViewModel by viewModels {
        HealthViewModelFactory((applicationContext as HealthApp).repository)
    }

    private val args: SecondFragmentArgs? by navArgs()
    private var health: Health? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = context.applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        health = args?.eHealth
        if (health != null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Update"
            binding.namaEditText.setText(health?.nama)
            binding.alamatEditText.setText(health?.alamat)
            binding.notelpTextMultiLine.setText(health?.notelp)
            val label = "Edit Data"
            navController.currentDestination?.label = label
        }else{
            val label = "Add Data"
            navController.currentDestination?.label = label
        }

        val nama = binding.namaEditText.text
        val alamat = binding.alamatEditText.text
        val notelp = binding.notelpTextMultiLine.text
        binding.saveButton.setOnClickListener {
            if(nama.isEmpty() || alamat.isEmpty() || notelp.isEmpty()){
                if (nama.isEmpty()){
                    Toast.makeText(context, "Name is required", Toast.LENGTH_SHORT).show()
                }
                if (alamat.isEmpty()){
                    Toast.makeText(context, "Address is required", Toast.LENGTH_SHORT).show()
                }
                if (notelp.isEmpty()){
                    Toast.makeText(context, "No Telpon is required", Toast.LENGTH_SHORT).show()
                }
            }else{
                if (health == null){
                    val health = Health(nama = nama.toString(), alamat = alamat.toString(), notelp = notelp.toString())
                    healthViewModel.insert(health)
                }else{
                    val health = Health(id = health?.id!!, nama = nama.toString(), alamat = alamat.toString(), notelp = notelp.toString())
                    healthViewModel.update(health)
                }
                findNavController().popBackStack()
            }
        }

        binding.deleteButton.setOnClickListener {
            healthViewModel.delete(health!!)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}