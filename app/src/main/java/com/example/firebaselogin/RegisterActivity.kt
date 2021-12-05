package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var editTextRepeatPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
        registerListeners()

    }


    private fun init(){
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
    }

    private fun registerListeners() {
        buttonRegister.setOnClickListener{

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password_2 = editTextRepeatPassword.text.toString()

            if(email.isEmpty() || password.isEmpty() || password_2.isEmpty() || password_2 != password || password.length < 9) {
                Toast.makeText(this, "პაროლის ან ელ. ფოსტის ველი ცარიელია ან პაროლები არ ემთხვევა ერთმანეთს!" +
                        " პაროლი უნდა შეიცავდეს მინიმუმ 9 სიმბოლოს!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "სამწუხაროდ თქვენ ვერ გაიარეთ რეგისტრაცია!", Toast.LENGTH_SHORT).show()
                    }

                }

            }

        }
    }

