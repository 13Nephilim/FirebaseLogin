package com.example.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PasswordChangeActivity : AppCompatActivity() {

    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonChange: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_change)

        init()

        registerListeners()

    }

    private fun init(){
        editTextNewPassword = findViewById(R.id.editTextNewPassword)
        buttonChange = findViewById(R.id.buttonChange)
    }

    private fun registerListeners(){
        buttonChange.setOnClickListener{

            val newPassword = editTextNewPassword.text.toString()

            if (newPassword.isEmpty() || newPassword.length < 9) {
                Toast.makeText(this, "პაროლი ძალიან მოკლეა ან არ შეიცავს სიმბოლოებს!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            FirebaseAuth.getInstance()
                .currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener{task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "თქვენი პაროლი წარმატებით შეიცვალა!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "თქვენი პაროლის შეცვლა ვერ მოხერხდა!", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

}