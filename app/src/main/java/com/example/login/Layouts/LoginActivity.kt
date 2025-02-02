package com.example.login.Layouts
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.login.CredentialsManager
import com.example.login.databinding.LoginActivityBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        initButtons()


    }

    private fun initButtons() = with(binding){
        textViewRegister.setOnClickListener {
            finish()
            val intent = Intent(baseContext, CreateAccount::class.java)
            startActivity(intent)

        }

        loginPageNextButton.setOnClickListener{
            val email = inputEmail.text.toString().trim().lowercase()
            val password = inputPassword.text.toString()

            val isEmailValid = isEmailValid(email)
            val isPasswordValid = isPasswordValid(password)

            if (isEmailValid) {
                inputEmail.error = null
            } else
                inputEmail.error = "Invalid email"

            if (isPasswordValid) {
                inputPassword.error = null
            } else
                inputPassword.error = "Invalid password"

            if (isEmailValid && isPasswordValid) {


                    val isPasswordCorrect = CredentialsManager(baseContext).loginUser(email,password)

                    if (isPasswordCorrect) {
                        finish()
                        val intent = Intent(baseContext, MainActivity::class.java)
                        println( CredentialsManager(baseContext).getAllUsers())
                        startActivity(intent)

                    } else {
                        inputPassword.error = "Wrong email or password!"
                    }
            }

        }


    }

    private fun isEmailValid(email: String) = CredentialsManager(baseContext).isEmailValid(email)

    private fun isPasswordValid(password: String) = CredentialsManager(baseContext).isPasswordValid(password)
}
