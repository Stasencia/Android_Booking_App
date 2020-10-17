package com.andrukh.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.andrukh.booking.databinding.FragmentPersonalInformationBinding

class PersonalInformationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersonalInformationBinding>(
            inflater, R.layout.fragment_personal_information, container, false
        )
        binding.bookButton.setOnClickListener {
            val fullName: String =
                binding.editTextFirstName.text.toString() + " " + binding.editTextLastName.text.toString()
            val lPurpose: Boolean = binding.radioButtonLeisure.isChecked
            val travelType: String =
                if (lPurpose) binding.radioButtonLeisure.text.toString() else binding.radioButtonBusiness.text.toString()
            it.findNavController()
                .navigate(
                    PersonalInformationFragmentDirections.actionPersonalInformationFragmentToBookingResultFragment(
                        fullName,
                        travelType
                    )
                )
        }
        return binding.root
    }

}