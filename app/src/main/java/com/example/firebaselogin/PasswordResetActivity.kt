package com.example.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordResetActivity : AppCompatActivity() {

    private lateinit var  editTextEmail: EditText
    private lateinit var buttonSend: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_reset)

        init()
        registerListeners()

    }

    private fun init(){
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSend = findViewById(R.id.buttonSend)
    }

    private fun registerListeners() {
        buttonSend.setOnClickListener{
            val email = editTextEmail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "ჩაწერეთ ელ.ფოსტა!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener{task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "შეამოწმეთ ელ.ფოსტა ახალი პაროლისათვის!", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this, "სამწუხაროდ მონაცემთა გაგზავნა ვერ განხორციელდა!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

}