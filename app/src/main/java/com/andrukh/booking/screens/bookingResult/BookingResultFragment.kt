package com.andrukh.booking.screens.bookingResult

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.andrukh.booking.R
import com.andrukh.booking.databinding.FragmentBookingResultBinding
import kotlinx.android.synthetic.main.fragment_booking_result.*

class BookingResultFragment : Fragment() {

    private lateinit var viewModel: BookingResultViewModel
    private lateinit var viewModelFactory: BookingResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentBookingResultBinding>(
            inflater,
            R.layout.fragment_booking_result, container, false
        )

        val args by navArgs<BookingResultFragmentArgs>()

        viewModelFactory = BookingResultViewModelFactory(args.payerName, args.travelType)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(BookingResultViewModel::class.java)

        binding.bookingResultViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that the binding can observe LiveData updates
        binding.setLifecycleOwner(this)

        // Sets up event listening to change the UI when booking is canceled
        viewModel.eventBookingCanceled.observe(viewLifecycleOwner, Observer { isCanceled ->
            if (isCanceled) {
                this.cancelBookingUIChange()
            }
        })

        binding.resultNotifyButton.setOnClickListener {
            if (binding.resultNotifyButton.isChecked) {
                Toast.makeText(
                    context,
                    "You will be notified when the request is processed.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Notification cancelled.", Toast.LENGTH_SHORT).show()
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun cancelBookingUIChange() {
        layoutBookingDetails.visibility = View.GONE
        textHeader.text = "The request was cancelled"
    }

    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(
                getString(
                    R.string.shareBookingInfo,
                    viewModel.payerName.value,
                    viewModel.travelType.value
                )
            )
            .setType("text/plain")
            .intent
    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.booking_result_menu, menu)
        // check if the activity resolves
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}