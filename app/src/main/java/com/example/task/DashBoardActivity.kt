package com.example.task

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from Intent
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val genderId = intent.getIntExtra("gender", -1)
        val country = intent.getStringExtra("country")
        val city = intent.getStringExtra("city")

        // Display retrieved data
        binding.tvName.text = "Name: $name"
        binding.tvEmail.text = "Email: $email"
        binding.tvPassword.text = "Password: $password"
        binding.tvGender.text = "Gender ID: $genderId"
        binding.tvCountry.text = "Country: $country"
        binding.tvCity.text = "City: $city"

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
