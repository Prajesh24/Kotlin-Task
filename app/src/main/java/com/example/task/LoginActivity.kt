package com.example.task

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private val countries = arrayOf("Nepal", "Bhutan", "Pakistan", "China", "Australia", "Canada", "India")
    private val cities = arrayOf("Kathmandu", "Bhaktapur", "Lalitpur", "Kritipur", "Kanchanpur")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryAdapter = ArrayAdapter(this@LoginActivity,
            android.R.layout.simple_dropdown_item_1line,
            countries)
        binding.CountrySpinner.adapter = countryAdapter

        val cityAdapter = ArrayAdapter(this@LoginActivity,
            android.R.layout.simple_dropdown_item_1line,
            cities)
        binding.CityAuto.setAdapter(cityAdapter)

        binding.SubmitBtn.setOnClickListener {
            val fullName = binding.FullName.text.toString()
            val email = binding.Email.text.toString()
            val password = binding.Password.text.toString()
            val selectedCountry = binding.CountrySpinner.selectedItem?.toString()
            val selectedCity = binding.CityAuto.text.toString()

            if (fullName.isEmpty()) {
                binding.FullName.error = "Enter your full name"
            } else if (email.isEmpty()) {
                binding.Email.error = "Please enter email"
            } else if (password.isEmpty()) {
                binding.Password.error = "Please enter password"
            } else if (selectedCountry.isNullOrEmpty() || selectedCountry == "Select Country") {
                Toast.makeText(
                    this@LoginActivity,
                    "Please select a valid country",
                    Toast.LENGTH_LONG
                ).show()
            } else if (selectedCity.isEmpty() || !cities.contains(selectedCity)) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please select a valid city",
                    Toast.LENGTH_LONG
                ).show()
            } else if (!binding.checkBox2.isChecked) {
                Toast.makeText(
                    this@LoginActivity,
                    "Please agree to the terms and conditions",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this@LoginActivity, DashBoardActivity::class.java).apply {
                    putExtra("name", fullName)
                    putExtra("email", email)
                    putExtra("password", password)
                    putExtra("gender", binding.RadioGrp.checkedRadioButtonId)
                    putExtra("country", selectedCountry)
                    putExtra("city", selectedCity)
                }

                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
