package com.example.myapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.DialogInfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.create.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val pass = binding.pass.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_LONG).show()
            } else if (pass.length < 6) {
                Toast.makeText(this, "Mật khẩu phải chứa ít nhất 6 kí tự", Toast.LENGTH_LONG).show()
            } else {
                createUser(email, pass)
            }
        }

        binding.login.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val pass = binding.pass.text.toString().trim()

            signIn(email, pass)
        }
    }

    private fun signIn(email: String, pass: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    val db = FirebaseFirestore.getInstance()
                    val docRef = db.collection("users")
                        .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    docRef.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                showDialog(document.data.toString())
                            } else {
                                it.exception?.message
                            }
                        }
                        .addOnFailureListener { exception ->
                            exception
                        }
                } else {
                    it.exception
                }
            }
    }

    private fun showDialog(message: String) {
        val dialog = Dialog(this)
        val dialogBinding = DialogInfoBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.txt.text = message
        dialog.show()
    }

    private fun createUser(email: String, pass: String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(
            email,
            pass
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                val db = Firebase.firestore
                val user = UserModel(it.result.user?.uid.toString(), email, pass)
//                db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener { documentReference ->
//                        documentReference
//                    }
//                    .addOnFailureListener { e ->
//
//                    }

                db.collection("users")
                    .document("uuuudfdf")
                    .set(user)
                    .addOnSuccessListener { documentReference ->

                    }
                    .addOnFailureListener { e ->

                    }

            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }
}