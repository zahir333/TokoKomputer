package com.example.tokokomputer.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tokokomputer.R
import com.example.tokokomputer.application.ComputerApp
import com.example.tokokomputer.databinding.FragmentSecondBinding
import com.example.tokokomputer.model.Computer

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val computerViewModel: ComputerViewModel by viewModels {
        ComputerViewModelFactory((applicationContext as ComputerApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var computer: Computer? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
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

        computer = args.computer
        // kita cek jika computer null maka tampilan default nambah toko komputer
        // jika computer tidak null tampilan sedikit berubah ada tombol hapus dan bah
        if (computer != null) {
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
            binding.nameEditText.setText(computer?.name)
            binding.addressEditText.setText(computer?.address)
            binding.telephoneEditText.setText(computer?.telephone)
        }
        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        val telephone = binding.telephoneEditText.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty()) {
                Toast.makeText(context, "Nama tidak bolek kosong", Toast.LENGTH_LONG).show()
            } else if (address.isEmpty()) {
                Toast.makeText(context, "Alamat tidak bolek kosong", Toast.LENGTH_LONG).show()
            } else if (telephone.isEmpty()) {
                Toast.makeText(context, "Telephone tidak bolek kosong", Toast.LENGTH_LONG).show()
            }
            else {
                if ( computer == null ) {
                    val computer = Computer(0, name.toString(), address.toString(), telephone.toString())
                    computerViewModel.insert(computer)
                } else{
                    val computer = Computer(computer?.id!!, name.toString(), address.toString(), telephone.toString())
                    computerViewModel.update(computer)
                }
                findNavController().popBackStack()
            }
        }

        binding.deleteButton.setOnClickListener {
            computer?.let { computerViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}