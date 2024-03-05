package com.example.bixi.auth


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bixi.MainActivity
import com.example.bixi.R
import com.example.bixi.ux.BlurredProgressDialog
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: BlurredProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        val emailInput: TextInputEditText = findViewById(R.id.emailinput)
        val passwordInput: TextInputEditText = findViewById(R.id.pwdid)
        val loginButton: Button = findViewById(R.id.loginBtn)
        val switchSignup : MaterialTextView = findViewById(R.id.switchtosignup)

        progressDialog = BlurredProgressDialog(this, R.style.CustomProgressDialogTheme)


        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                progressDialog.show()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        progressDialog.dismiss()
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login successful, welcome!", Toast.LENGTH_SHORT).show()
                            // Create an Intent to start MainActivity
                            val intent = Intent(this, MainActivity::class.java)
                            // Start MainActivity
                            startActivity(intent)
                            // Finish LoginActivity so user can't go back to it by pressing the back button
                            finish()
                        } else {
                            // Handle different types of errors
                            val exception = task.exception
                            when {
                                exception is FirebaseAuthInvalidCredentialsException -> {
                                    Toast.makeText(this, "Invalid login credentials", Toast.LENGTH_SHORT).show()
                                }
                                exception is FirebaseAuthInvalidUserException -> {
                                    Toast.makeText(this, "Invalid user", Toast.LENGTH_SHORT).show()
                                }
                                exception is FirebaseAuthUserCollisionException -> {
                                    Toast.makeText(this, "User collision", Toast.LENGTH_SHORT).show()
                                }
                                else -> {
                                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        switchSignup.setOnClickListener {
            // Navigate to the register screen
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}