package com.example.login.Layouts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.databinding.CreateAccountBinding
import com.example.login.CredentialsManager


class CreateAccount : AppCompatActivity() {



    private lateinit var binding: CreateAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtons()

    }

    private fun initButtons() = with(binding){

        // Navigate to Login Page
        textViewLogIn.setOnClickListener {

            finish()
            val intent = Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)

        }

        registerPageNextButton.setOnClickListener{

            val email = inputRegisteredEmail.text.toString().trim().lowercase()
            val password = inputNewPass.text.toString()

            val isEmailValid = isEmailValid(email)
            val isPasswordValid = isPasswordValid(password)

            if (isEmailValid) {
                inputRegisteredEmail.error = null
            } else
                inputRegisteredEmail.error = "Invalid email format."

            if (isPasswordValid) {
                inputNewPass.error = null
            } else
                inputNewPass.error = "Password must be at least 3 characters long."



            if (isEmailValid && isPasswordValid) {

                val registration = CredentialsManager(baseContext).registerUser(email,password)

                if (registration){
                    println(CredentialsManager(baseContext).getAllUsers())
                    finish()

                    val intent = Intent(baseContext, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    inputRegisteredEmail.error = "Email is already taken"
                }

            }


        }

    }

    private fun isEmailValid(email: String) = CredentialsManager(baseContext).isEmailValid(email)

    private fun isPasswordValid(password: String) = CredentialsManager(baseContext).isPasswordValid(password)

}

