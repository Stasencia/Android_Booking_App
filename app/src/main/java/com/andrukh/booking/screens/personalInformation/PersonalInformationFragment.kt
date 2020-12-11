package com.andrukh.booking.screens.personalInformation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.andrukh.booking.R
import com.andrukh.booking.databinding.FragmentPersonalInformationBinding
import timber.log.Timber

class PersonalInformationFragment : Fragment() {

    private lateinit var viewModel: PersonalInformationViewModel

    @SuppressLint("BinaryOperationInTimber")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i(
            "onCreate is called." +
                    " This method is called his is when the fragment is created. This will only get called once."
        )
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i(
            "onCreateView is called. " +
                    "This is called between onCreate and onActivityCreated. when the system will draw the fragment for the first time when the " +
                    "fragment becomes visible again. You must return a view in this callback if your fragment has a UI."
        )
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersonalInformationBinding>(
            inflater,
            R.layout.fragment_personal_information, container, false
        )

        // Get the viewmodel
        viewModel = ViewModelProvider(this).get(PersonalInformationViewModel::class.java)

        binding.bookButton.setOnClickListener {
            viewModel.setPersonalInfo(
                binding.editTextFirstName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.radioButtonLeisure.isChecked
            )
            it.findNavController()
                .navigate(
                    PersonalInformationFragmentDirections.actionPersonalInformationFragmentToBookingResultFragment(
                        viewModel.payerName.value ?: "",
                        viewModel.travelType.value ?: ""
                    )
                )
        }

        // Buzzes when triggered with different buzz events
        viewModel.eventBuzz.observe(viewLifecycleOwner, Observer { buzzType ->
            if (buzzType != PersonalInformationViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })

        return binding.root
    }

    /**
     * Given a pattern, this method makes sure the device buzzes
     */
    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.i(
            "onAttach is called. When the fragment is first attached to the activity." +
                    " This is only called once during the lifecycle of the fragment."
        )
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.i(
            "onActivityCreated is called. Called when the activity onCreate method has returned and the activity has been initialized. " +
                    "If the fragment is added to an activity that's already created, this still gets called. " +
                    "It's purpose is to contain any code the requires the activity exists and it is called multiple times during the lifecycle of the fragment."
        )
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onStart() {
        super.onStart()
        Timber.i("onStart is called. Called right before the fragment is visible to the user.")
    }


    override fun onResume() {
        super.onResume()
        Timber.i("onResume is called. When the activity resumes the fragment. This means the fragment is visible, has focus and is running.")
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i(
            "onDestroyView is called. Unlike activities, fragment views are destroyed every time they go off screen. " +
                    "This is called after the view is no longer visible."
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy is called. Called when the Activity’s onDestroy is called.")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("onDetach is called. Called when the association between the fragment and the activity is destroyed.")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop is called. Very similar to Activity’s onStop. This callback is called when the user leaves your fragment.")
    }
}