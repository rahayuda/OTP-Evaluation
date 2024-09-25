package com.example.otp

import android.content.Intent // Tambahkan import ini
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonLogin: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonLogin = findViewById(R.id.buttonLogin)

        auth = FirebaseAuth.getInstance()

        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(email, password)
            } else {
                Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Kirim email verifikasi setelah pendaftaran berhasil
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { verifyTask ->
                            if (verifyTask.isSuccessful) {
                                Toast.makeText(this, "Email verifikasi telah dikirim ke: $email", Toast.LENGTH_SHORT).show()
                            } else {
                                // Tambahkan log untuk debugging
                                Log.e("FirebaseAuth", "Gagal mengirim email verifikasi: ${verifyTask.exception?.message}")
                                Toast.makeText(this, "Gagal mengirim email verifikasi: ${verifyTask.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    // Tambahkan log untuk debugging
                    Log.e("FirebaseAuth", "Gagal mendaftar: ${task.exception?.message}")
                    Toast.makeText(this, "Gagal mendaftar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginUser(email: String, password: String) {
        Log.d("MainActivity", "Attempting to log in with email: $email") // Log sebelum login
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login berhasil
                    Log.d("FirebaseAuth", "Login berhasil untuk: $email")

                    // Cek jika email terverifikasi
                    val user = auth.currentUser
                    if (user != null) {
                        if (user.isEmailVerified) {
                            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                            // Pindah ke aktivitas berikutnya
                            val intent = Intent(this, NextActivity::class.java)
                            startActivity(intent)
                            finish() // Menghapus aktivitas ini dari stack
                        } else {
                            Toast.makeText(this, "Silakan verifikasi email Anda terlebih dahulu.", Toast.LENGTH_SHORT).show()
                            Log.e("FirebaseAuth", "Email belum terverifikasi.")
                        }
                    }
                } else {
                    // Gagal login
                    Log.e("FirebaseAuth", "Gagal login: ${task.exception?.message}")
                    Toast.makeText(this, "Gagal login: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
