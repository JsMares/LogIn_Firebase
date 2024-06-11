package com.example.login_firebase.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_firebase.models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    fun login(email: String, password: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {  task ->
                        if (task.isSuccessful) {
                            onSuccess("OK")
                        } else {
                            onSuccess("ERROR")
                        }
                    }
            }
            catch (e: Exception) {
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    fun createUser(email: String, password: String, userName: String, lastNameUser: String, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUser(userName, lastNameUser)
                            onSuccess("OK")
                        } else {
                            onSuccess("ERROR")
                        }
                    }
            }
            catch (e: Exception) {
                Log.d("ERROR EN JETPACK", "ERROR: ${e.localizedMessage}")
            }
        }
    }

    private fun saveUser(userName: String, lastName: String) {
        val userId = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch {
            val user = UserModel(
                userId = userId.toString(),
                email = email.toString(),
                userName = userName,
                lastName = lastName
            )

            FirebaseFirestore.getInstance().collection("Users")
                .add(user)
                .addOnSuccessListener {
                    Log.d("Great!", "User created!")
                }
                .addOnFailureListener{
                    Log.d("ERROR", "User wasn't created")
                }
        }
    }
}