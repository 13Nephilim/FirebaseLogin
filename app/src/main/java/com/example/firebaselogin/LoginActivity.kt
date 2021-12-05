package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button
    private lateinit var buttonPasswordReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (FirebaseAuth.getInstance().currentUser != null) {
            gotoprofile()
        }

        setContentView(R.layout.activity_login)

        init()

        registerListeners()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonPasswordReset = findViewById(R.id.buttonPasswordReset)
    }

    private fun registerListeners() {
        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonPasswordReset.setOnClickListener{
            val intent = Intent(this, PasswordResetActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "ელ. ფოსტა ან პაროლი არ შეგიყვანიათ, ან იგი არასწორია!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        gotoprofile()
                    } else {
                        Toast.makeText(this, "მოცემული ექაუნთი არ არსებობს ან არასწორად გაქვთ ჩაწერილი!", Toast.LENGTH_SHORT).show()
                    }


                }

        }
    }

    private fun gotoprofile() {

        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()

    }

}