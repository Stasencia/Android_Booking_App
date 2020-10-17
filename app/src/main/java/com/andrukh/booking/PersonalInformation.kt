package com.andrukh.booking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.andrukh.booking.databinding.ActivityPersonalInformationBinding

class PersonalInformation : AppCompatActivity() {
    private lateinit var binding: ActivityPersonalInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_personal_information)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_personal_information)

        binding.bookButton.setOnClickListener {
            onButtonClick(it)
        }
    }

    private fun onButtonClick(view: View) {
        /*val intent: Intent = Intent(baseContext, BookingResultActivity::class.java)
        intent.putExtra("FirstName", binding.editTextFirstName.text.toString());
        intent.putExtra("LastName", binding.editTextLastName.text.toString());
        val lPurpose: Boolean = binding.radioButtonLeisure.isChecked
        intent.putExtra(
            "Purpose",
            (if (lPurpose) binding.radioButtonLeisure.text.toString() else binding.radioButtonBusiness.text.toString())
        );
        view.context.startActivity(intent)*/
    }


}