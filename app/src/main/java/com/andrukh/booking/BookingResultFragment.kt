package com.andrukh.booking

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import com.andrukh.booking.databinding.ActivityBookingResultBinding
import com.andrukh.booking.databinding.FragmentBookingResultBinding

class BookingResultFragment : Fragment() {
    private lateinit var args: BookingResultFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentBookingResultBinding>(
            inflater, R.layout.fragment_booking_result, container, false
        )
        args = BookingResultFragmentArgs.fromBundle(requireArguments())
        binding.textPayer.text = args.payerName
        binding.textTravelType.text = args.travelType

        binding.resultNotifyButton.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    context,
                    "You will be notified when the request is processed.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(context, "Notification cancelled.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCancel.setOnClickListener {
            binding.layoutBookingDetails.visibility = View.GONE
            binding.textHeader.text = "The request was cancelled"
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(activity!!)
            .setText(getString(R.string.shareBookingInfo, args.payerName, args.travelType))
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