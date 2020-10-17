package com.andrukh.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.andrukh.booking.databinding.ActivityBookingResultBinding

class BookingResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_result)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_result)
        binding.resultNotifyButton.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                Toast.makeText(
                    this,
                    "You will be notified when the request is processed.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Notification cancelled.", Toast.LENGTH_SHORT).show()
            }
        }
        val firstName: String =
            "${intent.getStringExtra("FirstName").toString()} ${intent.getStringExtra("LastName")}"
        binding.textPayer.text = firstName
        binding.textTravelType.text = intent.getStringExtra("Purpose")

        binding.buttonCancel.setOnClickListener {
            binding.layoutBookingDetails.visibility = View.GONE
            binding.textHeader.text = "The request was cancelled"
        }
    }
}